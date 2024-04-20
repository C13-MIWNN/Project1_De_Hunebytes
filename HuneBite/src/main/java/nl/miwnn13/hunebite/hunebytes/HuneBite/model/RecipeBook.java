package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Entity //ivm arraylist kan het geen entity zijn, dus ik denk hier een aparte klasse voor maken?
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

    public long countRecipes() {
        if (recipesSet != null) {
            return recipesSet.size();
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
