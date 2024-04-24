package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
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
    private List<String> recipeSteps = new ArrayList<>();
    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();


    public String getTotalKcalOfRecipe() {
        if (recipeIngredientSet != null) {
            double totalKcal = 0.0;

            for (RecipeIngredient recipeIngredient : recipeIngredientSet) {
                totalKcal += recipeIngredient.getIngredient().getKcalPerUnit()
                        * recipeIngredient.getIngredientAmount();
            }

            return String.format("%.2f", totalKcal);
        }

        return "0,0";
    }
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

    public Set<RecipeIngredient> getRecipeIngredientSet() {
        return recipeIngredientSet;
    }

    public void setRecipeIngredientSet(Set<RecipeIngredient> recipeIngredientSet) {
        this.recipeIngredientSet = recipeIngredientSet;
    }
}
