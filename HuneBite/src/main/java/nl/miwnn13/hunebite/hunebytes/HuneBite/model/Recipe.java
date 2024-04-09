package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Justin Lamberts
 * Defines what a recipe is.
 **/

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;

    public Recipe(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe() {

    }
}
