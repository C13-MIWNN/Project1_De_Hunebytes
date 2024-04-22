package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Entity
public class RecipeBook {
    @Id @GeneratedValue
    private Long recipeBookId;
    private String recipeBookName;
    @ManyToMany
    private Set<Recipe> recipesSet;

    public RecipeBook(Long recipeBookId, String recipeBookName, Set<Recipe> recipesSet) {
        this.recipeBookId = recipeBookId;
        this.recipeBookName = recipeBookName;
        this.recipesSet = new HashSet<>();
    }

    public RecipeBook() {
    }

    public int countRecipes() {
        if (this.recipesSet != null) {
            return this.recipesSet.size();
        } else {
            return 0;
        }
    }

    public Long getRecipeBookId() {
        return recipeBookId;
    }

    public void setRecipeBookId(Long recipeBookId) {
        this.recipeBookId = recipeBookId;
    }

    public String getRecipeBookName() {
        return recipeBookName;
    }

    public void setRecipeBookName(String recipeBookName) {
        this.recipeBookName = recipeBookName;
    }

    public Set<Recipe> getRecipesSet() {
        return recipesSet;
    }

    public void setRecipesSet(Set<Recipe> recipes) {
        this.recipesSet = recipes;
    }

    @Override
    public String toString() {
        return recipeBookName;
    }

}
