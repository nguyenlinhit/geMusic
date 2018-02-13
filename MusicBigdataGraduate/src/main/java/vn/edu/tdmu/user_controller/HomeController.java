package vn.edu.tdmu.user_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.tdmu.enums.Country;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.models.SongPlaylist;
import vn.edu.tdmu.services.PlaylistService;
import vn.edu.tdmu.services.SongPlaylistService;
import vn.edu.tdmu.services.SongService;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenLinh on 2/8/2018.
 *
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final PlaylistService playlistService;
    private final SongService songService;
    private final SongPlaylistService songPlaylistService;
    @Autowired
    public HomeController(PlaylistService playlistService, SongService songService, SongPlaylistService songPlaylistService) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.songPlaylistService = songPlaylistService;
    }
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(@CookieValue(value = "recent", defaultValue = "") String recent, Locale locale, Model model, Principal principal){
        LOGGER.info("Welcome home! The client locale is {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        List<Playlist> slidePlaylists = playlistService.getSlideActivePlaylist();
        model.addAttribute("slidePlaylists", slidePlaylists);

        Page<Playlist> homePlaylists = playlistService.getHomePlaylists();
        model.addAttribute("homePlaylists", homePlaylists);

        List<Song> homeSongs = songService.getHomeSongs();
        model.addAttribute("homeSongs", homeSongs);

        Playlist topVNPl = playlistService.getLatestTopPlaylist(Country.VN.getCountry());
        model.addAttribute("topVNPl", topVNPl);
        List<SongPlaylist> topVN = songPlaylistService.findPlaylistOrderByOrderAsc(topVNPl);
        model.addAttribute("topVN", topVN);

        Playlist topUSPl = playlistService.getLatestTopPlaylist(Country.US.getCountry());
        model.addAttribute("topUSPl", topUSPl);
        List<SongPlaylist> topUS = songPlaylistService.findPlaylistOrderByOrderAsc(topVNPl);
        model.addAttribute("topUS", topUS);

        Playlist topKRPl = playlistService.getLatestTopPlaylist(Country.US.getCountry());
        model.addAttribute("topKRPl", topKRPl);
        List<SongPlaylist> topKR = songPlaylistService.findPlaylistOrderByOrderAsc(topVNPl);
        model.addAttribute("topKR", topKR);

        List<Playlist> recommendedPlaylists = playlistService.getRecommendedPlaylists(recent);
        model.addAttribute("recommendedPlaylists",recommendedPlaylists);

        if (principal != null){
            List<Playlist> userPlaylists = playlistService.getTop3UserPlaylists(principal.getName());
            model.addAttribute("userPlaylists", userPlaylists);
        }

        return "home/index";
    }
}
