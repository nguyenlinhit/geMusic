package vn.edu.tdmu.admin_controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdmu.enums.Country;
import vn.edu.tdmu.enums.PlaylistType;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.models.Genre;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.models.Views;
import vn.edu.tdmu.services.ArtistService;
import vn.edu.tdmu.services.GenreService;
import vn.edu.tdmu.services.SongService;
import vn.edu.tdmu.utils.GEMusicFunctions;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/song")
public class SongManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final String IMAGE_PATH;
    private final String IMAGE_DIRECTORY;

    private final SongService songService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Autowired
    SongManagementController(SongService songService, GenreService genreService, ServletContext servletContext, ArtistService artistService) {
        LOGGER.info("Inside constructor of SongManagementController.");
        this.songService = songService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.ABSTRACT_PATH = "/static/data/mp3/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
        this.IMAGE_PATH = "/static/img/song/";
        this.IMAGE_DIRECTORY = servletContext.getRealPath(IMAGE_PATH) + "/";
    }

    //-------------------Retrieve All Songs---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listSongs(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("title", "Manage Songs | GEMusic Administration");
        return "admin/song/list";
    }

    //-------------------Retrieve Single Song-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("title", "Song Details | GEMusic Administration");
        return "admin/song/details";
    }

    //-------------------Create a Song--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newSong(Model model) {
        Song song = new Song();
        model.addAttribute("song", song);
        model.addAttribute("title", "Add a new Song | GEMusic Administration");

        return "admin/song/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @RequestParam String save,
                           @RequestParam(required = false) String resource, @RequestParam MultipartFile appFile, @RequestParam MultipartFile gFile,
                           @RequestParam MultipartFile image, Model model, RedirectAttributes redirectAttributes) throws IOException {
        boolean hasErrors = false;
        if (image.getSize() > 1024000) {
            model.addAttribute("imgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (result.hasErrors() || hasErrors) {
            return "admin/song/create";
        }
        //Upload Image
        if (!image.isEmpty()) {
            String uploaded = GEMusicFunctions.uploadFile(image, IMAGE_DIRECTORY);
            String imageUrl = IMAGE_PATH + uploaded;
            song.setImageUrl(imageUrl);
        }
        //Upload mp3 file
        if (resource.equals("application")) {
            if (appFile.isEmpty() || appFile.getSize() > 10024000) {
                model.addAttribute("appFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/create";
            }
            //Upload file to application
            String uploaded = GEMusicFunctions.uploadFile(appFile, DIRECTORY);
            String fileUrl = ABSTRACT_PATH + uploaded;
            song.setUrl(fileUrl);
        } else if (resource.equals("google")) {
            if (gFile.isEmpty() || gFile.getSize() > 10024000) {
                model.addAttribute("gFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/create";
            }
            //Upload file to google drive
        }

        Song r = songService.create(song);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New song has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Song ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editSong(@PathVariable Integer id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("title", "Edit Song details | GEMusic Administration");
        return "admin/song/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Song song, BindingResult result, @PathVariable Integer id, @RequestParam String save,
                         @RequestParam(required = false) String resource, @RequestParam MultipartFile appFile, @RequestParam MultipartFile gFile, Model model,
                         RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "admin/song/edit";
        }

        if (resource.equals("application")) {
            if (appFile.isEmpty() || appFile.getSize() > 10024000) {
                model.addAttribute("appFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/edit";
            }
            //Upload file to application
            String uploaded = GEMusicFunctions.uploadFile(appFile, DIRECTORY);
            String fileUrl = ABSTRACT_PATH + uploaded;
            //Change SongUrl
            song.setUrl(fileUrl);
        } else if (resource.equals("google")) {
            if (gFile.isEmpty() || gFile.getSize() > 10024000) {
                model.addAttribute("gFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/edit";
            }
            //Upload file to google drive
        }

        songService.update(song);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This song has been changed successfully.");
            return "redirect:edit-" + song.getId();
        }

        return "redirect:list";
    }

    //------------------- Get Artists of Song -------------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/GetArtists", method = RequestMethod.GET)
    public Set<Artist> getMappedArtists(@PathVariable Integer id) {
        Song song = songService.findById(id);
        if (song != null) {
            return song.getArtists();
        }
        return null;
    }

    //------------------- Mapping Artist To Song------------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/AddArtist", method = RequestMethod.POST)
    public Artist mappingArtist(@PathVariable Integer id, @RequestParam Artist artist) {
        return songService.addArtist(id, artist);
    }

    //------------------- Remove Artist From Song ---------------------------------------------
    @ResponseBody
    @RequestMapping(value = "/{id}/RemoveArtist", method = RequestMethod.POST)
    public String removeMappedArtist(@PathVariable Integer id, @RequestParam Artist artist) {
        Boolean removed = songService.removeArtist(id, artist);
        return removed.toString();
    }

    //------------------- Delete a Song ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteSong(@PathVariable Integer id, Model model) {
        songService.delete(id);
        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "genres")
    private List<Genre> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute(value = "artists")
    private List<Artist> initializeArtists() {
        return artistService.findAll();
    }

    @ModelAttribute(value = "songArtists")
    private List<Artist> getSongArtists() {
        return artistService.findAll();
    }

    @ModelAttribute(value = "countries")
    private List<String> initializeCountries() {
        LOGGER.info("Create countries ModelAttribute");

        List<String> countries = new ArrayList<String>();
        countries.add(Country.US.getCountry());
        countries.add(Country.UK.getCountry());
        countries.add(Country.VN.getCountry());
        countries.add(Country.FR.getCountry());
        countries.add(Country.KR.getCountry());
        countries.add(Country.CA.getCountry());
        countries.add(Country.UNKNOWN.getCountry());
        return countries;
    }

    @ModelAttribute(value = "songTypes")
    private List<String> initializeSongTypes() {
        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());
        types.add(PlaylistType.USER.getPlaylistType());
        types.add(PlaylistType.TOP.getPlaylistType());
        types.add(PlaylistType.HOT.getPlaylistType());
        return types;
    }
}
