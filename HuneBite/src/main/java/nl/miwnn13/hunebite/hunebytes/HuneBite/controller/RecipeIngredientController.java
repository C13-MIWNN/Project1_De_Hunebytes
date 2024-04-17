package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
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
 * Handles everything to do with the intermediate table between Recipes and Ingredients
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

        recipeIngredientToBeSaved.setRecipe(recipe.get());

        Recipe thisRecipe = recipeIngredientToBeSaved.getRecipe();
        Ingredient thisIngredient = recipeIngredientToBeSaved.getIngredient();

        if (recipeIngredientRepository.findByRecipeAndIngredient(thisRecipe, thisIngredient).isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientRepository.
                    findByRecipeAndIngredient(thisRecipe, thisIngredient).get();

            recipeIngredient.setIngredientAmount(recipeIngredientToBeSaved.getIngredientAmount());

            recipeIngredientRepository.save(recipeIngredient);

            StringBuilder urlString = new StringBuilder();
            urlString.append("redirect:/recipe/").append(recipeTitle).append("/add/ingredients");

            return urlString.toString();
        }

        if (!result.hasErrors()) {
            recipeIngredientRepository.save(recipeIngredientToBeSaved);
        } else {
            return "redirect:/";
        }

        StringBuilder urlString = new StringBuilder();
        urlString.append("redirect:/recipe/").append(recipeTitle).append("/add/ingredients");

        return urlString.toString();
    }
}
