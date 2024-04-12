package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeBookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Controller
public class RecipeBookController {
    private final RecipeBookRepository recipeBookRepository;

    public RecipeBookController(RecipeBookRepository recipeBookRepository) {
        this.recipeBookRepository = recipeBookRepository;
    }

@GetMapping({"/"})
    public String showOverviewPage(Model model) {
        model.addAttribute("RecipeBook", recipeBookRepository.findAll());

        return "homepageOverview";
    }
}
 