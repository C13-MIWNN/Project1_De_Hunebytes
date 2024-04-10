package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Handles everything with recipies on the webpage
 **/

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe/detail/{recipeTitle}")
    private String showRecipeDetail(@PathVariable("recipeTitle") String recipeTitle, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/homepageOverview";
        }

        model.addAttribute("recipeToBeShown", recipe.get());
        return "recipeDetail";
    }
}
