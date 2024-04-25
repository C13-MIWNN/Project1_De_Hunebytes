package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.*;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeBookRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.IngredientRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.TagRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.*;
import nl.miwnn13.hunebite.hunebytes.HuneBite.services.HunebyteUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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
        private final HunebyteUserService hunebyteUserService;

    public InitializeController(RecipeBookRepository recipeBookRepository,
                                IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                TagRepository tagRepository,
                                RecipeIngredientRepository recipeIngredientRepository, HunebyteUserService hunebyteUserService) {
        this.recipeBookRepository = recipeBookRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.hunebyteUserService = hunebyteUserService;
    }

    @EventListener
    private void seed(ContextRefreshedEvent event){
        if (hunebyteUserService.isNotInitialised()) {
            initializeDB();
        }
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        makeUser("Admin", "Admin");


        Recipe defaultRecipe1 = makeRecipe("Boiled Egg");
        Recipe defaultRecipe2 = makeRecipe("Big Boss Burger");
        Recipe defaultRecipe3 = makeRecipe("English breakfast");
        Recipe defaultRecipe4 = makeRecipe("Irish stew");
        Recipe defaultRecipe5 = makeRecipe("Dutch stamppot");

        Ingredient defaultIngredient = makeIngredient("Egg", UnitType.PIECE, 0, 6.2,4.4);
        Ingredient defaultIngredient1 = makeIngredient("Cheese", UnitType.GRAM, 0, 0.3,0.2);
        Ingredient defaultIngredient2 = makeIngredient("Melk", UnitType.MILLILITER, 0.044, 0.034,0.044);
        Ingredient defaultIngredient3 = makeIngredient("Thyme", UnitType.TEASPOON, 0, 0,0);
        Ingredient defaultIngredient4 = makeIngredient("Rice", UnitType.GRAM, 0.3, 0.1,0.1);

        RecipeBook favorites = makeRecipeBook("Favorites", defaultRecipe1);

        RecipeIngredient recipeIngredient1 = makeRecipeIngredient(defaultRecipe1, defaultIngredient, 2);

        return "redirect:/";
    }

    private HunebyteUser makeUser(String username, String password) {
        HunebyteUser user = new HunebyteUser();
        user.setUsername(username);
        user.setPassword(password);
        hunebyteUserService.saveUser(user);
        return user;
    }

    protected RecipeBook makeRecipeBook(String Name, Recipe recipe) {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setRecipeBookName(Name);

        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);
        recipeBook.setRecipesSet(recipeSet);
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

    private Ingredient makeIngredient(String ingredientName, UnitType unitType,
                                      double carbohydrates, double proteins, double fats
                                      ) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientName(ingredientName);
        ingredient.setUnitType(unitType);
        ingredient.setCarbohydrates(carbohydrates);
        ingredient.setProteins(proteins);
        ingredient.setFats(fats);

        ingredientRepository.save(ingredient);
        return ingredient;
    }

}

