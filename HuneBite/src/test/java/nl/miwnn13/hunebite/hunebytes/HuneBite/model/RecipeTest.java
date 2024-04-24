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
    void shouldShowCorrectTotalKcalsOfRecipeTestOne() {
        var testRecipe = makeRecipe("testRecipe");
        testRecipe.setRecipeIngredientSet(setupRecipeIngredientForTest(
                testRecipe, 4,10.0, 10));

        assertEquals("6800,00", testRecipe.getTotalKcalOfRecipe());
    }

    @Test
    @DisplayName("Should show correct total of kcal's for a recipe with 0 ingredients")
    void shouldShowCorrectTotalOfKcalsForARecipeWithNoIngredients() {
        var testRecipe = makeRecipe("testRecipe");
        Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();
        testRecipe.setRecipeIngredientSet(recipeIngredientSet);

        assertEquals("0,00", testRecipe.getTotalKcalOfRecipe());
    }

    @Test
    @DisplayName("Should show correct total of kcal's for a new recipe")
    void shouldShowCorrectTotalOfKcalsForANewRecipe() {
        var testRecipe = makeRecipe("testRecipe");

        assertEquals("0,0", testRecipe.getTotalKcalOfRecipe());
    }


    private Set<RecipeIngredient> setupRecipeIngredientForTest(Recipe recipe, int amountOfIngredients,
                                                          Double ingredientMacroValues, int ingredientAmount) {
        Set<Ingredient> testIngredients = IngredientsWithTheseMacroValues(amountOfIngredients, ingredientMacroValues);
        Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();

        for (Ingredient ingredient : testIngredients) {
            RecipeIngredient recipeIngredient = makeRecipeIngredient(recipe, ingredient, ingredientAmount);

            recipeIngredientSet.add(recipeIngredient);
        }

        return recipeIngredientSet;
    }

    private Recipe makeRecipe(String recipeTitle) {
        var recipe = new Recipe();
        recipe.setRecipeTitle(recipeTitle);

        return recipe;
    }

    private Ingredient makeIngredient(String ingredientName, Double carbs, Double proteins, Double fats) {
        var ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        ingredient.setCarbohydrates(carbs);
        ingredient.setProteins(proteins);
        ingredient.setFats(fats);

        return ingredient;
    }

    private RecipeIngredient makeRecipeIngredient(Recipe recipe,
                                              Ingredient ingredient,
                                              int ingredientAmount) {
        var recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setIngredientAmount(ingredientAmount);

        return recipeIngredient;
    }

    private Set<Ingredient> IngredientsWithTheseMacroValues(int amountOfIngredients, Double ingredientAmount) {
        Set<Ingredient> fourIngredients = new HashSet<>();

        for (int i = 0; i < amountOfIngredients; i++) {
            Ingredient testIngredient = makeIngredient("testIngredient",
                    ingredientAmount,
                    ingredientAmount,
                    ingredientAmount);

            fourIngredients.add(testIngredient);
        }

        return fourIngredients;
    }
}