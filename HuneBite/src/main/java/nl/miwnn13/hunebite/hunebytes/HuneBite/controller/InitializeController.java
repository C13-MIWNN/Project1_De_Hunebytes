package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeIngredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
    @SuppressWarnings("SameReturnValue")
    @Controller
    public class InitializeController {

        private final RecipeBookRepository recipeBookRepository;
        private final IngredientRepository ingredientRepository;
        private final RecipeRepository recipeRepository;
        private final TagRepository tagRepository;
        private final RecipeIngredientRepository recipeIngredientRepository;

    public InitializeController(RecipeBookRepository recipeBookRepository,
                                IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                TagRepository tagRepository,
                                RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeBookRepository = recipeBookRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }


    @GetMapping("/initialize")
    private String initializeDB() {


        RecipeBook favorites = makeRecipeBook("Favorites");
        RecipeBook favorites2 = makeRecipeBook("Favorites2");

        Recipe defaultRecipe1 = makeRecipe("Boiled Egg");
        Recipe defaultRecipe2 = makeRecipe("Big Boss Burger");
        Recipe defaultRecipe3 = makeRecipe("English breakfast");
        Recipe defaultRecipe4 = makeRecipe("Irish stew");
        Recipe defaultRecipe5 = makeRecipe("Dutch stamppot");

        Ingredient defaultIngredient = makeIngredient("Egg");
        Ingredient defaultIngredient1 = makeIngredient("Cheese");
        Ingredient defaultIngredient2 = makeIngredient("Garlic");
        Ingredient defaultIngredient3 = makeIngredient("Thyme");
        Ingredient defaultIngredient4 = makeIngredient("Rice");

        RecipeIngredient recipeIngredient1 = makeRecipeIngredient(defaultRecipe1, defaultIngredient, 2);

        return "redirect:/";
    }

    private RecipeBook makeRecipeBook(String title) {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setRecipeBookName(title);

        Set<RecipeBook> recipeBookSet = new HashSet<>();
        recipeBookSet.add(recipeBook);
        recipeBookRepository.save(recipeBook);
        return recipeBook;
    }

    private Recipe makeRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setRecipeTitle(name);
        recipe.setRecipeSteps(makeRecipeStep());
        recipe.setRecipeDescription(makeRecipeDescription());
        recipeRepository.save(recipe);
        return recipe;
    }

    private List<String> makeRecipeStep() {
        List<String> steps = new ArrayList<>();
        steps.add("*First you do this*");
        steps.add("*Secondly you do this*");
        steps.add("*Don't forget to do this*");
        steps.add("*And then you do this*");
        steps.add("*Finally you do this*");
        steps.add("*But also you do this*");
        steps.add("*Finish it by doing this*");

        return steps;
    }

    private String makeRecipeDescription() {
        return "*Insert life story and also a little bit about the actual recipe*";
    }

    private RecipeIngredient makeRecipeIngredient(Recipe recipe, Ingredient ingredient, int amount) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setIngredientAmount(amount);

        recipeIngredientRepository.save(recipeIngredient);

        return recipeIngredient;
    }

    private Ingredient makeIngredient(String ingredient) {
        Ingredient defaultingredient = new Ingredient();
        defaultingredient.setIngredientName(ingredient);
        defaultingredient.setIngredientDescription(makeIngredientDescription());
        defaultingredient.setUnitType(makeIngredientType());
        defaultingredient.setCalories(makeIngredientCalories());
        defaultingredient.setFats(makeIngredientFats());
        defaultingredient.setProteins(makeIngredientProteins());

        ingredientRepository.save(defaultingredient);
        return defaultingredient;
    }
    private String makeIngredientDescription() {
        return "Is yummy and makes you fat.";
    }

    private String makeIngredientType() {
        return "gram";
    }
    private double makeIngredientCalories() {
        return 1000;
    }
    private double makeIngredientFats() {
        return 1500;
    }
    private double makeIngredientProteins() {
        return 900.1;
    }
}

