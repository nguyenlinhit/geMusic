package vn.edu.tdmu.user_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.tdmu.enums.RoleType;
import vn.edu.tdmu.models.Role;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.services.RoleService;
import vn.edu.tdmu.services.UserService;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by NguyenLinh on 2/7/2018.
 *
 */
@Controller
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final UserService userService;
    private final RoleService roleService;
    private  final MessageSource messageSource;

    @Autowired
    public AccountController(UserService userService, RoleService roleService, MessageSource messageSource) {
        this.userService = userService;
        this.roleService = roleService;
        this.messageSource = messageSource;
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        LOGGER.info("Moving to login page.");
        return "account/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model){
        LOGGER.info("Moving to login page.");
        User user = new User();
        model.addAttribute("user", user);
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result,Model model){
        LOGGER.info("doRegister user: {}", user);
        if (result.hasErrors()){
            return "account/register";
        }

        if (!userService.isUsernameUnique(user.getId(), user.getUsername())){
            FieldError usernameError = new FieldError("user", "username",
                    messageSource.getMessage("UniqueUsername.user.username", new String[]{user.getUsername()}, Locale.getDefault()));
            result.addError(usernameError);
            return "account/register";
        }
        LOGGER.info("Nothing wrong with this user {}", user);

        Object useRole = roleService.findByType(RoleType.USER.getRoleType());
        if (useRole != null){
            user.getRoles().add((Role) useRole);
        }

        userService.create(user);
        return "account/register_successful";
    }

    @RequestMapping(value = "/forgot_password")
    public String forgotPasswordPage(Model model){
        return "account/forgot_password";
    }

    @RequestMapping(value = "/get-user-link", method = RequestMethod.GET)
    public String getUserLink(Model model){
        return "shared/_UserLinkPartial";
    }

    @RequestMapping(value = "/get-logout-form", method = RequestMethod.GET)
    public String getLogoutForm(Model model){
        return "shared/_LogoutFormPartial";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(Model model){
        return "shared/401";
    }
}
