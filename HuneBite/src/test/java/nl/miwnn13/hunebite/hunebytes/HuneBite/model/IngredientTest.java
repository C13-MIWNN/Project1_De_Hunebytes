package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @Test
    @DisplayName("Should calculate kCal for Ingredient without carbohydrates")
    void unitValuesShouldCalculateCalories1() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(0);
        ingredient.setProteins(1);
        ingredient.setFats(0.2);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 0) + (4 * 1) + (9 * 0.2);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient without protein")
    void unitValuesShouldCalculateCalories2() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(1);
        ingredient.setProteins(0);
        ingredient.setFats(0.4);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 1) + (4 * 0) + (9 * 0.4);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient without fat")
    void unitValuesShouldCalculateCalories3() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(1.1);
        ingredient.setProteins(1);
        ingredient.setFats(0);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 1.1) + (4 * 1) + (9 * 0);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
    @Test
    @DisplayName("Should calculate kCal for Ingredient")
    void unitValuesShouldCalculateCalories4() {
        //Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setCarbohydrates(1);
        ingredient.setProteins(0.1);
        ingredient.setFats(2);

        // Act
        // factors kcal per gram are assumed from the values 'Voedingscentrum' in the Netherlands uses.
        double expectedKcal = (4 * 1) + (4 * 0.1) + (9 * 2);

        double actualKcal = ingredient.getKcalPerUnit();

        //Assert
        assertEquals(expectedKcal, actualKcal);

    }
}