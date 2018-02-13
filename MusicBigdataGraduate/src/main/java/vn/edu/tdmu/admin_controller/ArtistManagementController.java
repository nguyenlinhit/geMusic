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
import vn.edu.tdmu.enums.Country;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.services.ArtistService;
import vn.edu.tdmu.utils.GEMusicFunctions;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenLinh on 2/12/2018.
 *
 */
@Controller
@RequestMapping("/admin/artist")
@SessionAttributes("countries")
public class ArtistManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final ArtistService artistService;
    final
    MessageSource messageSource;
    @Autowired
    public ArtistManagementController(ArtistService artistService, MessageSource messageSource, ServletContext servletContext) {
        LOGGER.info("Inside constructor of ArtistManagementController.");

        this.artistService = artistService;
        this.messageSource = messageSource;

        this.ABSTRACT_PATH = "/static/img/artist/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //Retrieve All Artists
    @RequestMapping(value = {"/", "list"})
    public String listArtist(Model model){
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Manage Artists | GEMusic Administration");

        return "admin/artist/list";

    }

    //Retrieve Single Artist
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model){
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Artist Details | GEMusic Administration");

        return "admin/artist/details";
    }

    //Create a Artist
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newArtist(Model model){
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Add a new Artist | GEMusic Administration");
        return "admin/artist/create_dpk";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveArtist(@Valid @ModelAttribute("artist") Artist artist, BindingResult result, @RequestParam String save,
                             @RequestParam MultipartFile image, @RequestParam MultipartFile cover, Model model, RedirectAttributes redirectAttributes) throws IOException {
        boolean hasErrors = false;
        if (!artistService.isArtistNameUnique(artist.getId(), artist.getName())){
            FieldError nameError = new FieldError("artist","name",
                    messageSource.getMessage("UniqueName.artist.name", new String[] {artist.getName()}, Locale.getDefault()));
            result.addError(nameError);
        }

        if (image.getSize() > 1024000){
            model.addAttribute("imageError", "File size must be less than 1 MB.");
            hasErrors = true;
        }

        if (cover.getSize() > 1024000){
            model.addAttribute("coverError", "File size must be less than 1 MB.");
            hasErrors = true;
        }

        if (result.hasErrors() || hasErrors){
            return "admin/artist/create_dpk";
        }

        if (!image.isEmpty()){
            String uploaded = GEMusicFunctions.uploadFile(image, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            artist.setImageUrl(imageUrl);
        }

        if (!cover.isEmpty()){
            String uploaded = GEMusicFunctions.uploadFile(cover, DIRECTORY);
            String coverUrl = ABSTRACT_PATH + uploaded;
            artist.setCoverImageUrl(coverUrl);
        }

        Artist r = artistService.create(artist);
        model.asMap().clear();

        if (save.equals("Create and Edit")){
            redirectAttributes.addFlashAttribute("success", "New artist has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }
    //Update a Artist
    @RequestMapping(value = "edit-{id}", method = RequestMethod.GET)
    public String editArtist(@PathVariable Integer id, Model model){
        Artist artist = artistService.findById(id);
        model.addAttribute("artist",artist);
        model.addAttribute("title", "Edit Artist details | GEMusic Administration");
        return "admin/artist/edit_dpk";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute Artist artist, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
                         RedirectAttributes redirectAttributes){
        if (!artistService.isArtistNameUnique(artist.getId(), artist.getName())){
            FieldError namError = new FieldError("artist", "name",
                    messageSource.getMessage("UniqueName.artist.name", new String[] {artist.getName()}, Locale.getDefault()));
            result.addError(namError);
        }

        if(result.hasErrors()){
            return "admin/artist/edit_dpk";
        }

        artistService.update(artist);
        model.asMap().clear();

        if (save.equals("Save and Continue")){
            redirectAttributes.addFlashAttribute("success", "This artist has been change successfully.");
            return "redirect:edit-"+artist.getId();
        }

        return "redirect:list";
    }

    //Delete a Artist
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteArtist(@PathVariable Integer id, Model model){
        artistService.delete(id);
        model.asMap().clear();

        return "redirect:list";
    }

    //Model Attributes
    private List<String> initializeCountries(){
        LOGGER.info("Create countries ModelAttributes");
        List<String> countries = new ArrayList<>();
        countries.add(Country.US.getCountry());
        countries.add(Country.UK.getCountry());
        countries.add(Country.VN.getCountry());
        countries.add(Country.FR.getCountry());
        countries.add(Country.KR.getCountry());
        countries.add(Country.CA.getCountry());
        countries.add(Country.UNKNOWN.getCountry());
        return countries;
    }
}
