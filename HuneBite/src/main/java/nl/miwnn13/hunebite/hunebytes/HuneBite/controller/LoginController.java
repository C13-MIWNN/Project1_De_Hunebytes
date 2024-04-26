package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Justin Lamberts
 * Handles loginpage
 **/
@Controller
public class LoginController {
    @GetMapping("/login")
    String login() {
        return "login";
    }
}

