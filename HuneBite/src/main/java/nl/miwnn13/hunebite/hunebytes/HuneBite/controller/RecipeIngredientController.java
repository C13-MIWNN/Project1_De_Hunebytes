package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeIngredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.IngredientRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeIngredientRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Author: Tim Bulder
 * <p>
 * purpose for class
 **/
@Controller
public class RecipeIngredientController {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeIngredientController(RecipeIngredientRepository recipeIngredientRepository,
                                      RecipeRepository recipeRepository,
                                      IngredientRepository ingredientRepository) {

        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/recipe/{recipeTitle}/add/ingredients")
    private String showRecipeAddIngredients(@PathVariable("recipeTitle") String recipeTitle, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeToAddTo", recipe.get());
        model.addAttribute("recipeIngredient", new RecipeIngredient());
        model.addAttribute("allIngredients", ingredientRepository.findAll());

        return "recipeIngredientForm";
    }

    @PostMapping("/recipe/{recipeTitle}/add/ingredients")
    private String saveRecipeIngredient(@ModelAttribute("recipeIngredient")
                                            RecipeIngredient recipeIngredientToBeSaved,
                                        BindingResult result,
                                        @PathVariable("recipeTitle") String recipeTitle) {
        Optional<Recipe> recipe = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        if (recipeIngredientToBeSaved.getRecipeIngredientId() == null
                && recipeRepository.findByRecipeTitle
                (recipeIngredientToBeSaved.getRecipe().getRecipeTitle()).isPresent()) {

            return "redirect:/recipe/new";
        }

        if (!result.hasErrors()) {
            recipeIngredientRepository.save(recipeIngredientToBeSaved);
        }

        return "redirect:/recipe/detail/" + recipeTitle;
    }
}
