package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

/**
 * Author: Tim Bulder
 * <p>
 * Handles everything to do with the intermediate table between Recipe's and Ingredients
 **/
@Entity
@Table(uniqueConstraints =
        { @UniqueConstraint(name = "UniqueRecipeAndIngredient",
                columnNames = { "recipe_recipe_id", "ingredient_ingredient_id" }) })
public class RecipeIngredient {
    @Id @GeneratedValue
    private Long recipeIngredientId;
    @ManyToOne
    private Recipe recipe;
    @ManyToOne
    private Ingredient ingredient;
    private int ingredientAmount;

    public Long getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(Long recipeIngredientAmountId) {
        this.recipeIngredientId = recipeIngredientAmountId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }
}
