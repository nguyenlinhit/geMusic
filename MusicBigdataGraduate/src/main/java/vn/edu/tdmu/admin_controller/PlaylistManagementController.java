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
import vn.edu.tdmu.models.*;
import vn.edu.tdmu.services.*;
import vn.edu.tdmu.utils.GEMusicFunctions;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/playlist")
public class PlaylistManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final PlaylistService playlistService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final WeekService weekService;
    private final SongPlaylistService songPlaylistService;

    @Autowired
    PlaylistManagementController(PlaylistService playlistService, ArtistService artistService, GenreService genreService, WeekService weekService,
                                 SongPlaylistService songPlaylistService, ServletContext servletContext) {
        LOGGER.info("Inside constructor of PlaylistManagementController.");

        this.playlistService = playlistService;
        this.artistService = artistService;
        this.genreService = genreService;
        this.weekService = weekService;
        this.songPlaylistService = songPlaylistService;
        this.ABSTRACT_PATH = "/static/img/playlist/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //-------------------Retrieve All Playlists-------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listPlaylists(Model model) {
        List<Playlist> playlists = playlistService.findAll();
        model.addAttribute("playlists", playlists);
        model.addAttribute("title", "Manage Playlists | GEMusic Administration");
        return "admin/playlist/list";
    }

    //-------------------Retrieve Single Playlist-----------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Playlist Details | GEMusic Administration");
        return "admin/playlist/details";
    }

    //-------------------Create a Playlist------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newPlaylist(Model model) {
        Playlist playlist = new Playlist();
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Add a new Playlist | GEMusic Administration");
        return "admin/playlist/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String savePlaylist(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, @RequestParam String save,
                               @RequestParam MultipartFile image, @RequestParam MultipartFile slideImage, Model model, RedirectAttributes redirectAttributes)
            throws IOException {
        boolean hasErrors = false;
        if (image.getSize() > 1024000) {
            model.addAttribute("imgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (slideImage.getSize() > 1024000) {
            model.addAttribute("slideImgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (result.hasErrors() || hasErrors) {
            return "admin/playlist/create";
        }

        if (!image.isEmpty()) {
            String uploaded = GEMusicFunctions.uploadFile(image, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            playlist.setImageUrl(imageUrl);
        }

        if (!slideImage.isEmpty()) {
            String uploaded = GEMusicFunctions.uploadFile(slideImage, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            playlist.setSlideImageUrl(imageUrl);
        }

        Playlist r = playlistService.create(playlist);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New playlist has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Playlist ----------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editPlaylist(@PathVariable Integer id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Edit Playlist details | GEMusic Administration");
        return "admin/playlist/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Playlist playlist, BindingResult result, @PathVariable Integer id, @RequestParam String save,
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/playlist/edit";
        }

        playlistService.update(playlist);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This playlist has been changed successfully.");
            return "redirect:edit-" + playlist.getId();
        }

        return "redirect:list";
    }

    //------------------- Mapping Song To Playlist----------------------------------------------
    @RequestMapping(value = "/{id}/AddSongs", method = RequestMethod.GET)
    public String mappingSongs(@PathVariable Integer id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        return "admin/playlist/list_song";
    }

    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/AddSongs", method = RequestMethod.POST)
    public List<SongPlaylist> mappingSongs(@PathVariable Integer id, @RequestParam List<Song> songs) {
        Playlist pl = playlistService.findById(id);

        List<SongPlaylist> lsSongPlaylist = new ArrayList<SongPlaylist>();
        for (Song song : songs) {
            SongPlaylist sp = new SongPlaylist();
            sp.setPlaylist(pl);
            sp.setSong(song);
            sp.setIndex(0);

            lsSongPlaylist.add(sp);
        }

        return songPlaylistService.create(lsSongPlaylist);
    }

    //------------------- Get All Songs of Playlist --------------------------------------------
    @JsonView(Views.ExtendedPublic.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/GetSongPlaylists", method = RequestMethod.GET)
    public List<SongPlaylist> getSongs(@PathVariable Integer id) {
        Playlist playlist = playlistService.findById(id);
        return songPlaylistService.findByPlaylist(playlist);
    }

    //------------------- Edit order of SongPlaylist--------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/UpdateSongPlaylist-{songPlaylistId}", method = RequestMethod.POST)
    public SongPlaylist changeSongOrder(@PathVariable Integer id, @PathVariable Integer songPlaylistId, @RequestParam Integer order) {
        return songPlaylistService.changeOrder(songPlaylistId, order);
    }

    //------------------- Remove Song From Playlist---------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/RemoveSongPlaylist-{songPlaylistId}", method = RequestMethod.GET)
    public SongPlaylist removeMappedSong(@PathVariable Integer id, @PathVariable Integer songPlaylistId) {
        return songPlaylistService.remove(songPlaylistId);
    }

    //------------------- Delete a Playlist ---------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deletePlaylist(@PathVariable Integer id, Model model) {
        playlistService.delete(id);
        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "artists")
    private List<Artist> initializeArtists() {
        return artistService.findAll();
    }

    @ModelAttribute(value = "genres")
    private List<Genre> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute(value = "weeks")
    private List<Week> initializeWeeks() {
        return weekService.findAll();
    }

    @ModelAttribute(value = "plTypes")
    private List<String> initializePlaylistTypes() {
        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());
        types.add(PlaylistType.USER.getPlaylistType());
        types.add(PlaylistType.TOP.getPlaylistType());
        types.add(PlaylistType.HOT.getPlaylistType());
        return types;
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
}
