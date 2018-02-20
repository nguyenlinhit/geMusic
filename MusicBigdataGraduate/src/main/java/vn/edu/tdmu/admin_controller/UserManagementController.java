package vn.edu.tdmu.admin_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdmu.enums.State;
import vn.edu.tdmu.models.Role;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.services.RoleService;
import vn.edu.tdmu.services.UserService;
import vn.edu.tdmu.utils.GEMusicFunctions;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserManagementController(UserService userService, RoleService roleService, ServletContext servletContext) {
        LOGGER.info("Inside constructor of UserManagementController.");

        this.userService = userService;
        this.roleService = roleService;
        this.ABSTRACT_PATH = "/static/img/user/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //-------------------Retrieve All Users----------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "Manage Users | GEMusic Administration");
        return "admin/user/list";
    }

    //-------------------Retrieve Single User--------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "User Details | GEMusic Administration");
        return "admin/user/details";
    }

    //-------------------Create a User---------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Add a new User | GEMusic Administration");
        return "admin/user/create_dpk";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam String save,
                           @RequestParam MultipartFile image, Model model, RedirectAttributes redirectAttributes) throws IOException {
        boolean hasErrors = false;

        if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
            FieldError usernameError = new FieldError("user", "username",
                    messageSource.getMessage("UniqueUsername.user.username", new String[] { user.getUsername() }, Locale.getDefault()));
            result.addError(usernameError);
        }
        if (!userService.isEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email",
                    messageSource.getMessage("UniqueEmail.user.email", new String[] { user.getEmail() }, Locale.getDefault()));
            result.addError(emailError);
        }
        if (image.getSize() > 1024000) {
            model.addAttribute("imgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (result.hasErrors() || hasErrors) {
            return "admin/user/create_dpk";
        }

        if (!image.isEmpty()) {
            String uploaded = GEMusicFunctions.uploadFile(image, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            user.setImageUrl(imageUrl);
        }

        LOGGER.info("Nothing wrong with this user {}", user);
        User u = userService.create(user);
        //hide params in url
        model.asMap().clear();

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New user has been created successfully.");
            return "redirect:edit-" + u.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "Edit User details | GEMusic Administration");
        return "admin/user/edit_dpk";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute User user, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
                         RedirectAttributes redirectAttributes) {
        LOGGER.info("Starting edit the user {}", user);
        //No need check Username Unique because that field is readonly, does not allow update.

        if (!userService.isEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email",
                    messageSource.getMessage("UniqueEmail.user.email", new String[] { user.getEmail() }, Locale.getDefault()));
            result.addError(emailError);
        }
        if (result.hasErrors()) {
            return "admin/user/edit_dpk";
        }

        LOGGER.info("Nothing wrong with this user {}", user);
        userService.update(user);
        model.asMap().clear();

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This user has been changed successfully.");
            return "redirect:edit-" + user.getId();
        }

        return "redirect:list";
    }

    //------------------- Update User Image ----------------------------------------------------
    @RequestMapping(value = "/update-avatar-{id}", method = RequestMethod.POST)
    public String updateAvatar(@PathVariable Integer id, @RequestParam MultipartFile newImage, Model model, RedirectAttributes redirectAttributes)
            throws IOException {
        if (newImage.isEmpty()) {
            redirectAttributes.addFlashAttribute("failed", "File is missing.");
            model.asMap().clear();
            return "redirect:edit-" + id;
        }
        if (newImage.getSize() > 1024000) {
            redirectAttributes.addFlashAttribute("failed", "File size must be less than 1 MB.");
            model.asMap().clear();
            return "redirect:edit-" + id;
        }

        String uploaded = GEMusicFunctions.uploadFile(newImage, DIRECTORY);
        String imageUrl = ABSTRACT_PATH + uploaded;
        userService.updateAvatar(id, imageUrl);
        model.asMap().clear();

        redirectAttributes.addFlashAttribute("success", "The avatar has been changed successfully.");
        return "redirect:edit-" + id;
    }

    //------------------- Update User Password ------------------------------------------------
    @RequestMapping(value = "/update-password-{id}", method = RequestMethod.POST)
    public String updatePassword(@PathVariable Integer id, @RequestParam String newPassword, Model model, RedirectAttributes redirectAttributes) {
        if (newPassword.length() < 6) {
            redirectAttributes.addFlashAttribute("failed", "Password must be at least 6 characters.");
            model.asMap().clear();
            return "redirect:edit-" + id;
        }

        LOGGER.info("\n\nUpdating User Password\n");
        userService.updatePassword(id, newPassword);
        model.asMap().clear();

        redirectAttributes.addFlashAttribute("success", "The password has been changed successfully.");
        return "redirect:edit-" + id;
    }

    //------------------- Delete a User --------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id, Model model) {
        userService.delete(id);
        model.asMap().clear();

        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "roles")
    private List<Role> initializeRoles() {
        LOGGER.info("Create roles ModelAttribute");
        return roleService.findAll();
    }

    @ModelAttribute(value = "states")
    private List<String> initializeStates() {
        LOGGER.info("Create states ModelAttribute");

        List<String> states = new ArrayList<String>();
        states.add(State.ACTIVE.getState());
        states.add(State.DELETED.getState());
        states.add(State.INACTIVE.getState());
        states.add(State.LOCKED.getState());
        return states;
    }
}
