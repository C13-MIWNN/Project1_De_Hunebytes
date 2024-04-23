package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @Test
    @DisplayName("Should calculate kCal for Ingredient")
    void unitValuesShouldCalculateCalories1() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(0);
        ingredient.setProteins(10);
        ingredient.setFats(2);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 0) + (4 * 10) + (9 * 2);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient")
    void unitValuesShouldCalculateCalories2() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(10);
        ingredient.setProteins(0);
        ingredient.setFats(2);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 10) + (4 * 0) + (9 * 2);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient")
    void unitValuesShouldCalculateCalories3() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(10);
        ingredient.setProteins(10);
        ingredient.setFats(0);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 10) + (4 * 10) + (9 * 0);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient")
    void unitValuesShouldCalculateCalories4() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(10);
        ingredient.setProteins(10);
        ingredient.setFats(2);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 10) + (4 * 10) + (9 * 2);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
}