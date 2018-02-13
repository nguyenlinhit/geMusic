package vn.edu.tdmu.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdmu.services.UserService;

/**
 * Created by NguyenLinh on 2/7/2018.
 *
 */
@RestController
@RequestMapping("/rest/available")
public class CheckAvailable {
    private final UserService userService;

    @Autowired
    public CheckAvailable(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String isUserAvailable(Integer id, @RequestParam String username){
        Boolean available = userService.isUsernameUnique(id, username);

        if(available){
            return available.toString();
        }else {
            return "Such username already exists.";
        }
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String isEmailAvailable(Integer id, @RequestParam String email){
        Boolean available = userService.isEmailUnique(id, email);

        if(available){
            return available.toString();
        }else {
            return "Such username already exists.";
        }
    }
}
