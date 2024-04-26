package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

//    @GetMapping("/logout")
//    String logout() {
//        return "homepageOverview";
//    }
}

