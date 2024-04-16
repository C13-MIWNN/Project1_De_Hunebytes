package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

/**
 * Author: Tim Bulder
 * <p>
 * purpose for class
 **/
@Entity
@Table(uniqueConstraints =
        { @UniqueConstraint(name = "UniqueRecipeAndIngredient",
                columnNames = { "recipe_recipe_id", "ingredient_ingredient_id" }) })
public class RecipeIngredient {
    @Id @GeneratedValue
    private Long recipeIngredientAmountId;
    @ManyToOne
    private Recipe recipe;
    @ManyToOne
    private Ingredient ingredient;
    private int ingredientAmount;

    public Long getRecipeIngredientAmountId() {
        return recipeIngredientAmountId;
    }

    public void setRecipeIngredientAmountId(Long recipeIngredientAmountId) {
        this.recipeIngredientAmountId = recipeIngredientAmountId;
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
