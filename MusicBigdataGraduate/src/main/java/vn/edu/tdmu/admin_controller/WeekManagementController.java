package vn.edu.tdmu.admin_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdmu.models.Week;
import vn.edu.tdmu.services.WeekService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by NguyenLinh on 2/13/2018.
 *
 */
@Controller
@RequestMapping("/admin/week")
public class WeekManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeekManagementController.class);

    private final WeekService weekService;

    @Autowired
    WeekManagementController(WeekService weekService) {
        LOGGER.info("Inside constructor of WeekManagementController.");
        this.weekService = weekService;
    }

    //-------------------Retrieve All Weeks---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listWeeks(Model model) {
        List<Week> weeks = weekService.findAll();
        model.addAttribute("weeks", weeks);
        model.addAttribute("title", "Manage Weeks | FMusic Administration");
        return "admin/week/list";
    }

    //-------------------Retrieve Single Week-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Week week = weekService.findById(id);
        model.addAttribute("week", week);
        model.addAttribute("title", "Week Details | FMusic Administration");
        return "admin/week/details";
    }

    //-------------------Create a Week--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newWeek(Model model) {
        Week week = new Week();
        model.addAttribute("week", week);
        model.addAttribute("title", "Add a new Week | FMusic Administration");
        return "admin/week/create_dpk";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveWeek(@Valid @ModelAttribute("week") Week week, BindingResult result, @RequestParam String save, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/week/create_dpk";
        }

        Week r = weekService.create(week);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New week has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Week ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editWeek(@PathVariable Integer id, Model model) {
        Week week = weekService.findById(id);
        model.addAttribute("week", week);
        model.addAttribute("title", "Edit Week details | FMusic Administration");
        return "admin/week/edit_dpk";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Week week, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/week/edit_dpk";
        }

        weekService.update(week);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This week has been changed successfully.");
            return "redirect:edit-" + week.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Week ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteWeek(@PathVariable Integer id, Model model) {
        weekService.delete(id);
        return "redirect:list";
    }
}
