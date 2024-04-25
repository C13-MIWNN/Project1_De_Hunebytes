package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import nl.miwnn13.hunebite.hunebytes.HuneBite.controller.InitializeController;
import nl.miwnn13.hunebite.hunebytes.HuneBite.controller.RecipeBookController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.thymeleaf.engine.IterationStatusVar;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
class RecipeBookTest {

    @Test
    @DisplayName("Test an Empty Book")
    public void TestEmptyBook() {
        RecipeBook recipeBook = new RecipeBook();
        assertEquals(0, recipeBook.countRecipes());
    }

    @Test
    @DisplayName("Test if the recipebook returns 0 when the set is null")
    public void TestWhenRecipeBookIsNull() {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setRecipesSet(null);
        assertEquals(0, recipeBook.countRecipes());
    }

    @Test
    @DisplayName("Test if 100 recipes are added")
    void TestIf100RecipesAreAdded() {
        RecipeBook recipeBook = new RecipeBook();
        Set<Recipe> recipeSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            recipeSet.add(new Recipe());
        }
        recipeBook.setRecipesSet(recipeSet);
        assertEquals(100, recipeBook.countRecipes());
    }

    @Test
    @DisplayName("Test passes if the results do not match expectation")
    void TestIfResultsDontMatchExpectations() {
        RecipeBook recipeBook = new RecipeBook();
        Set<Recipe> recipeSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            recipeSet.add(new Recipe());
        }
        recipeBook.setRecipesSet(recipeSet);
        assertNotEquals(99, recipeBook.countRecipes());
    }
}
