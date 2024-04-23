package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mareth Westhoff
 * Represents an ingredient which can be addded to a recipe
 * Constants for factors kcal per gram are assumed from the values, 'Voedingscentrum' in the Netherlands, uses.
 **/

@Entity
public class Ingredient {

    public static final int CARB_TO_KCAL_FACTOR_PER_GRAM = 4;
    public static final int PROTEIN_TO_KCAL_FACTOR_PER_GRAM = 4;
    public static final int FAT_TO_KCAL_FACTOR_PER_GRAM = 9;
    @Id @GeneratedValue
    private Long ingredientId;

    @Column(unique=true)
    private String ingredientName;

    private String ingredientDescription;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REMOVE)
    private Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();


    private double carbohydrates;
    private double proteins;
    private double fats;

    public Ingredient(Long ingredientId, String ingredientName, UnitType unitType,
                      double carbohydrates, double proteins, double fats,
                      Set<RecipeIngredient> recipeIngredientSet, String ingredientDescription) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.unitType = unitType;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
        this.recipeIngredientSet = recipeIngredientSet;
        this.ingredientDescription = ingredientDescription;
    }

    public Ingredient() {
    }

    public double getKcalPerUnit(){
       return CARB_TO_KCAL_FACTOR_PER_GRAM * carbohydrates +
               PROTEIN_TO_KCAL_FACTOR_PER_GRAM * proteins +
               FAT_TO_KCAL_FACTOR_PER_GRAM * fats;
    }

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

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double calories) {
        this.carbohydrates = calories;
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
