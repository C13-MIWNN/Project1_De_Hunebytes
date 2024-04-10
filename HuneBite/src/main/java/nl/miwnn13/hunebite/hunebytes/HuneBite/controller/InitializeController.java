package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.BookRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.IngredientRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
    @SuppressWarnings("SameReturnValue")
    @Controller
    public class InitializeController {

        private final BookRepository bookRepository;
        private final IngredientRepository ingredientRepository;
        private final RecipeRepository recipeRepository;
        private final TagRepository tagRepository;

    public InitializeController(BookRepository bookRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
    }


    @GetMapping("/initialize")
    private String initializeDB() {


        RecipeBook favorites = makeRecipeBook("Favorites");

        Recipe defaultRecipe = makeRecipe("Gekookt ei");

        Ingredient defaultIngredient = makeIngredient("Ei");

        return "redirect:/";
    }

    private RecipeBook makeRecipeBook(String title) {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setTitle(title);

        Set<RecipeBook> authorSet = new HashSet<>();
        authorSet.add(recipeBook);
        RecipeBook.setRecipeBook(authorSet);

        bookRepository.save(recipeBook);
        return recipeBook;
    }

    private Recipe makeRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setname = new Recipe();
        recipeRepository.save(recipe);
        return recipe;
    }

    private Ingredient makeIngredient(String ingredient) {
        Ingredient defaultingredient = new Ingredient();
        defaultingredient.setingredient();
        ingredientRepository.save(defaultingredient);
        return defaultingredient;
    }
}

