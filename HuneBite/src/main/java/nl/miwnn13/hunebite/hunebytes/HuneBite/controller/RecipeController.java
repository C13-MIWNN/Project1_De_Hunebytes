package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;


import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
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
 * @author Justin Lamberts
 * Handles everything with recipies on the webpage
 **/


@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeController(RecipeRepository recipeRepository,
                            IngredientRepository ingredientRepository,
                            RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @GetMapping("/recipe/detail/{recipeTitle}")
    private String showRecipeDetail(@PathVariable("recipeTitle") String recipeTitle, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeToBeShown", recipe.get());
        model.addAttribute("allRecipeIngredients", recipeIngredientRepository.findAll());
        return "recipeDetail";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allIngredients", ingredientRepository.findAll());


        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveRecipe(@ModelAttribute("recipe") Recipe recipeToBeSaved, BindingResult result) {
        if (recipeToBeSaved.getRecipeId() == null
                && recipeRepository.findByRecipeTitle(recipeToBeSaved.getRecipeTitle()).isPresent()) {
            return "redirect:/recipe/new";
        }

        if (!result.hasErrors()) {
            recipeRepository.save(recipeToBeSaved);
        }

        String recipeTitle = recipeToBeSaved.getRecipeTitle();
        StringBuilder urlString = new StringBuilder();

        urlString.append("redirect:/recipe/").append(recipeTitle).append("/add/ingredients");

        return urlString.toString();
    }

    @GetMapping("/recipe/edit/{recipeTitle}")
    private String showRecipeEditForm(@PathVariable("recipeTitle") String recipeTitle, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipe", recipe.get());

        return "recipeForm";
    }

    @GetMapping("/recipe")
    private String ShowAllRecipes(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

}
