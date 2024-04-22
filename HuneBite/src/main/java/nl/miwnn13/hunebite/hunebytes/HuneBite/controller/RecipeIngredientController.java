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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                                        @RequestParam("ingredient") List<Long> ingredientIds,
                                        @PathVariable("recipeTitle") String recipeTitle) {
        Optional<Recipe> recipe = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        for (Long ingredientId : ingredientIds) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            recipeIngredientToBeSaved = new RecipeIngredient();
            recipeIngredientToBeSaved.setRecipe(recipe.get());

            if (ingredient.isPresent()) {
                recipeIngredientToBeSaved.setIngredient(ingredient.get());
                if (recipeIngredientRepository.findByRecipeAndIngredient(recipeIngredientToBeSaved.getRecipe(), recipeIngredientToBeSaved.getIngredient()).isPresent()) {
                    recipeIngredientToBeSaved.setIngredient(null);
                } else if (!result.hasErrors()) {
                    recipeIngredientRepository.save(recipeIngredientToBeSaved);
                } else {
                    return "redirect:/";
                }
            }
        }

        String returnUrl = "redirect:/recipe/" + recipeTitle + "/add/ingredients";
        return returnUrl;
    }

    @RequestMapping(value = "/recipeingredient/setamount/{id}", method = RequestMethod.POST)
    public String setRecipeIngredientAmount(@ModelAttribute("recipeIngredient")
                                            RecipeIngredient recipeIngredientToBeSaved,
                                            @PathVariable Long id) {
        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            String recipeTitle = recipeIngredient.getRecipe().getRecipeTitle();

            recipeIngredient.setIngredientAmount(recipeIngredientToBeSaved.getIngredientAmount());

            recipeIngredientRepository.save(recipeIngredient);

            String returnUrl = "redirect:/recipe/" + recipeTitle + "/add/ingredients";
            return returnUrl;

        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/recipeingredient/delete/{id}", method = RequestMethod.POST)
    public String deleteRecipeIngredient(@PathVariable Long id) {
        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            String recipeTitle = recipeIngredient.getRecipe().getRecipeTitle();

            recipeIngredientRepository.deleteById(id);

            String returnUrl = "redirect:/recipe/" + recipeTitle + "/add/ingredients";
            return returnUrl;
        } else {
            return "redirect:/";

        }
    }
}
