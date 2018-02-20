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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdmu.models.Genre;
import vn.edu.tdmu.services.GenreService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/genre")
public class GenreManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenreManagementController.class);

    private final GenreService genreService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    GenreManagementController(GenreService genreService) {
        LOGGER.info("Inside constructor of GenreManagementController.");
        this.genreService = genreService;
    }

    //-------------------Retrieve All Genres---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listGenres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("title", "Manage Genres | GEMusic Administration");
        return "admin/genre/list";
    }

    //-------------------Retrieve Single Genre-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        model.addAttribute("title", "Genre Details | GEMusic Administration");
        return "admin/genre/details";
    }

    //-------------------Create a Genre--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newGenre(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        model.addAttribute("title", "Add a new Genre | GEMusic Administration");
        return "admin/genre/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveGenre(@Valid @ModelAttribute("genre") Genre genre, BindingResult result, @RequestParam String save, Model model,
                            RedirectAttributes redirectAttributes) {
        if (!genreService.isGenreNameUnique(genre.getId(), genre.getName())) {
            FieldError nameError = new FieldError("genre", "name",
                    messageSource.getMessage("UniqueName.genre.name", new String[] { genre.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (result.hasErrors()) {
            return "admin/genre/create";
        }

        Genre r = genreService.create(genre);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New genre has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Genre ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editGenre(@PathVariable Integer id, Model model) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        model.addAttribute("title", "Edit Genre details | GEMusic Administration");
        return "admin/genre/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Genre genre, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
                         RedirectAttributes redirectAttributes) {
        if (!genreService.isGenreNameUnique(genre.getId(), genre.getName())) {
            FieldError nameError = new FieldError("genre", "name",
                    messageSource.getMessage("UniqueName.genre.name", new String[] { genre.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (result.hasErrors()) {
            return "admin/genre/edit";
        }

        genreService.update(genre);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This genre has been changed successfully.");
            return "redirect:edit-" + genre.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Genre ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteGenre(@PathVariable Integer id, Model model) {
        genreService.delete(id);
        return "redirect:list";
    }
}
