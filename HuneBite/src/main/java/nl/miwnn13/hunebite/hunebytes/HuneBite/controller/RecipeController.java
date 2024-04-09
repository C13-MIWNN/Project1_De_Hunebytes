package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Justin Lamberts
 * Handles everything with recipies on the webpage
 **/

@Controller
public class RecipeController {
    private final nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.recipeRepository recipeRepository;
    public RecipeController(nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.recipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    private String showRecipe(Model model) {
        model.addAttribute("Recipes", recipeRepository.findAll());
        return "recipeOverview";
    }
}
