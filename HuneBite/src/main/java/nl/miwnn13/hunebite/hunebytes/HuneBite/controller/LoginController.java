package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Controller
public class LoginController {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/logout")
    String logout() {
        return "redirect:/";
    }
}
