package vn.edu.tdmu.admin_controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.services.UserService;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@ControllerAdvice
@Component
public class GlobalControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    private UserService userService;


    @ModelAttribute("loginModel")
    public User loginModel() {
        return getPrincipal();
    }

    private User getPrincipal() {
        LOGGER.debug("Getting the user of authenticated user.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            LOGGER.debug("Current user is anonymous. Returning null.");
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);
            LOGGER.debug("Returning user: {}", user);
            return user;
        } else {
            return null;
        }
    }
}
