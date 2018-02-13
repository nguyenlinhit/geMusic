package vn.edu.tdmu.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.models.Views;
import vn.edu.tdmu.services.SongService;

import java.util.List;

/**
 * Created by NguyenLinh on 2/7/2018.
 *
 */
@RestController
@RequestMapping("/rest/song")
public class SongControllerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongControllerApi.class);

    private final SongService songService;

    @Autowired
    public SongControllerApi(SongService songService) {
        this.songService = songService;
        LOGGER.info("Inside constructor of SongControllerApi");
    }
    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Song findById(@PathVariable("id") Integer id){
        return songService.findById(id);
    }
    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Song> finfAll(){
        return songService.findAll();
    }
}
