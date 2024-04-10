package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Justin Lamberts
 * Defines what a recipe is.
 **/

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;
    private String recipeTitle;
    private String recipeDescription;
    @ElementCollection @OrderColumn
    private List<String> recipeSteps;
    @ManyToMany
    private Set<Ingredient> ingredients;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public List<String> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<String> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
