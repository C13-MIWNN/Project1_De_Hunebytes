package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.HunebyteUser;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.HunebyteUserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public class ProfileController {
    private final HunebyteUserRepository hunebyteUserRepository;

    public ProfileController(HunebyteUserRepository hunebyteUserRepository) {
        this.hunebyteUserRepository = hunebyteUserRepository;
    }


    @GetMapping({"/profile"})
    public String showOverviewPage(Model model, HunebyteUser hunebyteUser) {
        model.addAttribute("allUsers", hunebyteUserRepository.findAll());

        return "ProfilePage";
    }
}
