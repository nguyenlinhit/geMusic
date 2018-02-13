package vn.edu.tdmu.admin_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.services.SongService;

import java.util.List;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin")
public class DashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    private final SongService songService;

    @Autowired
    public DashboardController(SongService songService) {
        LOGGER.debug("Inside constructor of DashboardController");
        this.songService = songService;
    }

    @RequestMapping(value = {"","/dashboard"}, method = RequestMethod.GET)
    public String admin(Model model){
        List<Song> songs = songService.getNewUserUploadSong();
        model.addAttribute("songs", songs);
        return "admin/dashboard/index";
    }
}
