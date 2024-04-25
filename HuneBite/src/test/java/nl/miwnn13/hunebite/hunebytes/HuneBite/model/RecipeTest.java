package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Tim Bulder
 * <p>
 * Here are all tests written for the Recipe Model
 **/
class RecipeTest {

    @Test
    @DisplayName("Should show correct total of kcal's for a recipe with 10 of each 4 ingredients," +
            " that have their macro values set to 10")
    void shouldShowCorrectTotalKcalSOfRecipeTestOne() {
        var testRecipe = makeRecipe("testRecipe");
        testRecipe.setRecipeIngredientSet(setupRecipeIngredientForTest(
                testRecipe, 4,10.0, 10));

        assertEquals(6800.00, testRecipe.getTotalKcalOfRecipe(), 0.0001);
    }

    @Test
    @DisplayName("Should show correct total of kcal's for a recipe with 5 of each 3 ingredients, " +
            "that have carbs, proteins and fats set to 2.21, 1.62 and 0.63 respectively")
    void shouldShowCorrectTotalOfKcalSForARecipeWithFiveOfEachThreeIngredientsWithDifferentMacroValues() {
        var testRecipe = makeRecipe("testRecipe");
        testRecipe.setRecipeIngredientSet(setupRecipeIngredientForTestWithDifferentMacroValues(
            testRecipe,
            3,
            2.21,
            1.62,
            0.63,
            5));

        assertEquals(314.85, testRecipe.getTotalKcalOfRecipe(), 0.0001);

    }

    @Test
    @DisplayName("Should show correct total of kcal's for a recipe, " +
            "that has 6 of 1 ingredient with carbs, proteins and fat set to 2.21, 1.62 and null respectively")
    void shouldShowCorrectTotalOfKcalSForARecipeThatHasSixOfOneIngredientWithOneMacroValueSetToNull() {
        var testRecipe = makeRecipe("testRecipe");

        var testIngredient = new Ingredient();
        testIngredient.setCarbohydrates(2.21);
        testIngredient.setProteins(1.62);

        var recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientAmount(6);
        recipeIngredient.setRecipe(testRecipe);
        recipeIngredient.setIngredient(testIngredient);

        testRecipe.getRecipeIngredientSet().add(recipeIngredient);

        assertEquals(91.92, testRecipe.getTotalKcalOfRecipe(), 0.0001);
    }

    @Test
    @DisplayName("Should show correct total kcal's for a recipe with 10 of each 4 ingredients, " +
            "that have no macro values set")
    void shouldShowCorrectTotalKcalSForARecipeWithTenOfEachFourIngredientsThatHaveNoMacroValuesSet() {
        var testRecipe = makeRecipe("testRecipe");
        testRecipe.setRecipeIngredientSet(setupRecipeIngredientForTest(
                testRecipe, 4,0.0, 10));

        assertEquals(0.00, testRecipe.getTotalKcalOfRecipe());
    }

    @Test
    @DisplayName("Should show correct total of kcal's for a recipe with 0 ingredients")
    void shouldShowCorrectTotalOfKcalSForARecipeWithNoIngredients() {
        var testRecipe = makeRecipe("testRecipe");
        Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();
        testRecipe.setRecipeIngredientSet(recipeIngredientSet);

        assertEquals(0.00, testRecipe.getTotalKcalOfRecipe());
    }

    private Recipe makeRecipe(String recipeTitle) {
        var recipe = new Recipe();
        recipe.setRecipeTitle(recipeTitle);

        return recipe;
    }

    private Ingredient makeIngredient(String ingredientName, double carbs, double proteins, double fats) {
        var ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        ingredient.setCarbohydrates(carbs);
        ingredient.setProteins(proteins);
        ingredient.setFats(fats);

        return ingredient;
    }

    private RecipeIngredient makeRecipeIngredient(
        Recipe recipe,
        Ingredient ingredient,
        int ingredientAmount) {

        var recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setIngredientAmount(ingredientAmount);

        return recipeIngredient;
    }

    private Set<Ingredient> makeIngredientSetWithTheseMacroValues(
        int amountOfIngredients,
        double ingredientMacroValue) {

        Set<Ingredient> ingredientSet = new HashSet<>();

        for (int i = 0; i < amountOfIngredients; i++) {
            Ingredient testIngredient = makeIngredient(
                "testIngredient",
                ingredientMacroValue,
                ingredientMacroValue,
                ingredientMacroValue);

            ingredientSet.add(testIngredient);
        }

        return ingredientSet;
    }

    private Set<Ingredient> makeIngredientSetWithDifferentMacroValues(
        int amountOfIngredients,
        double ingredientCarbValue,
        double ingredientProteinValue,
        double ingredientFatValue) {

        Set<Ingredient> ingredientSet = new HashSet<>();

        for (int i = 0; i < amountOfIngredients; i++) {
            Ingredient testIngredient = makeIngredient(
                "testIngredient",
                ingredientCarbValue,
                ingredientProteinValue,
                ingredientFatValue);

            ingredientSet.add(testIngredient);
        }

        return ingredientSet;
    }

    private Set<RecipeIngredient> setupRecipeIngredientForTest(
            Recipe recipe,
            int amountOfIngredients,
            double ingredientMacroValues,
            int ingredientAmount) {

        Set<Ingredient> testIngredients = makeIngredientSetWithTheseMacroValues(
                amountOfIngredients, ingredientMacroValues);
        Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();

        for (Ingredient ingredient : testIngredients) {
            RecipeIngredient recipeIngredient = makeRecipeIngredient(recipe, ingredient, ingredientAmount);

            recipeIngredientSet.add(recipeIngredient);
        }

        return recipeIngredientSet;
    }

    private Set<RecipeIngredient> setupRecipeIngredientForTestWithDifferentMacroValues(
        Recipe recipe,
        int amountOfIngredients,
        double ingredientCarbValue,
        double ingredientProteinValue,
        double ingredientFatValue ,
        int ingredientAmount) {

        Set<Ingredient> testIngredients = makeIngredientSetWithDifferentMacroValues(
                amountOfIngredients,
                ingredientCarbValue,
                ingredientProteinValue,
                ingredientFatValue);

        Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();

        for (Ingredient ingredient : testIngredients) {
            RecipeIngredient recipeIngredient = makeRecipeIngredient(recipe, ingredient, ingredientAmount);

            recipeIngredientSet.add(recipeIngredient);
        }

        return recipeIngredientSet;
    }
}