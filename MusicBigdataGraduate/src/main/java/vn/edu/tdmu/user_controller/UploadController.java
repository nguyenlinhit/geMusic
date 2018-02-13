package vn.edu.tdmu.user_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdmu.enums.PlaylistType;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.services.ArtistService;
import vn.edu.tdmu.services.GenreService;
import vn.edu.tdmu.services.SongService;
import vn.edu.tdmu.utils.GEMusicFunctions;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by NguyenLinh on 2/12/2018.
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final SongService songService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Autowired
    public UploadController(SongService songService, GenreService genreService, ArtistService artistService, ServletContext servletContext) {
        LOGGER.info("Inside constructor of UploadController");
        ABSTRACT_PATH = "/static/data/mp3/";
        DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
        this.songService = songService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String upload(Model model){
        Song song = new Song();
        model.addAttribute("song",song);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("artists",artistService.findAll());

        return "upload/form";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doUpload(@Valid @ModelAttribute("song") Song song, BindingResult result, @RequestParam MultipartFile file, Model model) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("genres",genreService.findAll());
            model.addAttribute("artists",artistService.findAll());
            return "upload.form";
        }

        //Validate file
        if (file.isEmpty() || file.getSize() > 10024000){
            model.addAttribute("fileError", "File cannot null and file size must be less than 10 MB.");
            model.addAttribute("genres",genreService.findAll());
            model.addAttribute("artists", artistService.findAll());

            return "upload/form";
        }

        //Upload file
        String uploaded = GEMusicFunctions.uploadFile(file, DIRECTORY);
        String fileUrl = ABSTRACT_PATH + uploaded;
        song.setUrl(fileUrl);

        song.setType(PlaylistType.USER.getPlaylistType());
        songService.create(song);

        return "upload/success";
    }
}
