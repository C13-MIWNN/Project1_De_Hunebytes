package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Entity //ivm arraylist kan het geen entity zijn, dus ik denk hier een apparte klasse voor maken?
public class RecipeBook {
    @Id @GeneratedValue
    private Long recipeBookId;
    private String recipeBookName;
    //private ArrayList<Recipe> recipes;

    public RecipeBook(Long recipeBookId, String recipeBookName) {
        this.recipeBookId = recipeBookId;
        this.recipeBookName = recipeBookName;
        //this.recipes = new ArrayList<>();
    }

    public RecipeBook() {
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
}
