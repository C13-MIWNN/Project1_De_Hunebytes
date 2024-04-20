package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mareth Westhoff
 * Represents an ingredient which can be addded to a recipe
 **/

@Entity
public class Ingredient {
    @Id @GeneratedValue
    private Long ingredientId;

    @Column(unique=true)
    private String ingredientName;

    private String ingredientDescription;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();

    private double calories;
    private double proteins;
    private double fats;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public UnitType getUnitType() {
        return unitType;
    }


    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public Set<RecipeIngredient> getRecipeIngredientSet() {
        return recipeIngredientSet;
    }

    public void setRecipeIngredientSet(Set<RecipeIngredient> recipeIngredientSet) {
        this.recipeIngredientSet = recipeIngredientSet;
    }
}
