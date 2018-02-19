package vn.edu.tdmu.user_controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdmu.models.Genre;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.models.SongPlaylist;
import vn.edu.tdmu.services.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 * Created by NguyenLinh on 2/9/2018.
 *
 */
@Controller
@RequestMapping("/playlist")
public class PlaylistController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistController.class);

    private final GenreService genreService;
    private final PlaylistService playlistService;
    private final SongPlaylistService songPlaylistService;
    private final SongService songService;
    private final UserService userService;

    @Autowired
    public PlaylistController(GenreService genreService, PlaylistService playlistService, SongPlaylistService songPlaylistService, SongService songService, UserService userService) {
        LOGGER.info("Inside PlaylistController constructor");
        this.genreService = genreService;
        this.playlistService = playlistService;
        this.songPlaylistService = songPlaylistService;
        this.songService = songService;
        this.userService = userService;
    }

    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Genre> genres = genreService.findAll();

        Page<Playlist> page = playlistService.getAllOfficialAndCollectionPlaylists(1);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 7, page.getTotalPages());

        model.addAttribute("currentGenre", "All");
        model.addAttribute("genres", genres);
        model.addAttribute("page", page);
        model.addAttribute("beginIndex",begin);
        model.addAttribute("endIndex",end);
        model.addAttribute("currentIndex", current);

        return "playlist/index";
    }

    @RequestMapping(value = {"/{genre}/{pageNumber}"}, method = RequestMethod.GET)
    public String index(@PathVariable String genre, @PathVariable Integer pageNumber, Model model){
        List<Genre> genres = genreService.findAll();

        Page<Playlist> page;
        if (genre.equals("All")){
            page = playlistService.getAllOfficialAndCollectionPlaylists(pageNumber);
        }else {
            page = playlistService.getPlaylistByGenreName(genre, pageNumber);
        }

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 7, page.getTotalPages());

        model.addAttribute("currentGenre", genre);
        model.addAttribute("genres", genres);
        model.addAttribute("page", page);
        model.addAttribute("beginIndex",begin);
        model.addAttribute("endIndex",end);
        model.addAttribute("currentIndex", current);

        return "playlist/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, HttpServletResponse response, Model model) throws JsonProcessingException {
        Playlist playlist = playlistService.getById(id);
        if(playlist == null){
            //excrption
        }

        List<SongPlaylist> songPlaylists = songPlaylistService.findPlaylistOrderByOrderAsc(playlist);
        if (songPlaylists.size() == 0){
            //exception
        }

        assert playlist != null;
        List<Playlist> relatedPlaylists = playlistService.getRelatedPlaylists(playlist.getArtist());
        List<Song> relatedSongs = songService.getRelatedSongs(playlist.getArtist());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new Hibernate5Module());
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(songPlaylists);

        model.addAttribute("playlist", playlist);
        model.addAttribute("songPlaylists", json);
        model.addAttribute("relatedPlaylists", relatedPlaylists);
        model.addAttribute("relatedSongs", relatedSongs);

        return "playlist/details";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPlaylistToUser(@RequestParam Integer id, Principal principal){
        if (principal == null){
            return "unauthorized";
        }

        Playlist playlist = playlistService.findById(id);
        boolean b = userService.addPlaylistToUser(principal.getName(), playlist);

        if (b){
            return "success";
        }else{
            return "fail";
        }
    }
}
