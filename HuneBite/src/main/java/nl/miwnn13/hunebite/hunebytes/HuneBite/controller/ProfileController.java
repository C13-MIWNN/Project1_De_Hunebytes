package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.HunebyteUser;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.HunebyteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Handles everything related to the user page.
 **/
@Controller
public class ProfileController {
    private final HunebyteUserRepository hunebyteUserRepository;

    public ProfileController(HunebyteUserRepository hunebyteUserRepository) {
        this.hunebyteUserRepository = hunebyteUserRepository;
    }

    @GetMapping("/profile/{username}")
    private String showProfileDetail(@PathVariable("username") String username, Model model) {
        Optional<HunebyteUser> byUsername = hunebyteUserRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("User", byUsername);

        return "profilePage";
    }
}
