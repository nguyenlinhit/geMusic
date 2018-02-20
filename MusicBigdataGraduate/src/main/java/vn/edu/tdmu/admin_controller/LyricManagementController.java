package vn.edu.tdmu.admin_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdmu.models.Lyric;
import vn.edu.tdmu.services.LyricService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/lyric")
public class LyricManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LyricManagementController.class);

    private final LyricService lyricService;

    @Autowired
    LyricManagementController(LyricService lyricService) {
        LOGGER.info("Inside constructor of LyricManagementController.");
        this.lyricService = lyricService;
    }

    //-------------------Retrieve All Lyrics---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listLyrics(Model model) {
        List<Lyric> lyrics = lyricService.findAll();
        model.addAttribute("lyrics", lyrics);
        model.addAttribute("title", "Manage Lyrics | GEMusic Administration");
        return "admin/lyric/list";
    }

    //-------------------Retrieve Single Lyric-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Lyric lyric = lyricService.findById(id);
        model.addAttribute("lyric", lyric);
        model.addAttribute("title", "Lyric Details | GEMusic Administration");
        return "admin/lyric/details";
    }

    //-------------------Create a Lyric--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newLyric(Model model) {
        Lyric lyric = new Lyric();
        model.addAttribute("lyric", lyric);
        model.addAttribute("title", "Add a new Lyric | GEMusic Administration");
        return "admin/lyric/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveLyric(@Valid @ModelAttribute("lyric") Lyric lyric, BindingResult result, @RequestParam String save, Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/lyric/create";
        }

        Lyric r = lyricService.create(lyric);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New lyric has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Lyric ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editLyric(@PathVariable Integer id, Model model) {
        Lyric lyric = lyricService.findById(id);
        model.addAttribute("lyric", lyric);
        model.addAttribute("title", "Edit Lyric details | GEMusic Administration");
        return "admin/lyric/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Lyric lyric, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/lyric/edit";
        }

        lyricService.update(lyric);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This lyric has been changed successfully.");
            return "redirect:edit-" + lyric.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Lyric ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteLyric(@PathVariable Integer id, Model model) {
        lyricService.delete(id);
        return "redirect:list";
    }
}
