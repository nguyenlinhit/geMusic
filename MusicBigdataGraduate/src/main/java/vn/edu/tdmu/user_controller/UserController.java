package vn.edu.tdmu.user_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.services.PlaylistService;
import vn.edu.tdmu.services.UserService;

import java.util.List;

/**
 * Created by NguyenLinh on 2/12/2018.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final PlaylistService playlistService;
    private final UserService userService;

    @Autowired
    public UserController(PlaylistService playlistService, UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/{username}"}, method = RequestMethod.GET)
    public String index(@PathVariable String username, Model model){
        User user = userService.findByUsername(username);
        if (user != null){
            //throws exception
        }

        List<Playlist> userPlaylists = playlistService.getAllUserPlaylists(username);

        model.addAttribute("user", user);
        model.addAttribute("userPlaylists", userPlaylists);

        return "user/index";
    }

}
