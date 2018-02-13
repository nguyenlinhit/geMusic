package vn.edu.tdmu.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.models.Views;
import vn.edu.tdmu.services.UserService;

import java.util.List;

/**
 * Created by NguyenLinh on 2/7/2018.
 *
 */
@RestController
@RequestMapping("rest/user")
public class UserControllerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerApi.class);

    private final UserService userService;

    @Autowired
    public UserControllerApi(UserService userService) {
        LOGGER.info("Inside constructor of UserControllerApi");
        this.userService = userService;
    }

    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @JsonView(Views.Summary.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }
}
