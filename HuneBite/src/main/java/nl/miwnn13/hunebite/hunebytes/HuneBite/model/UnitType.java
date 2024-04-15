package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

/**
 * @author Mareth Westhoff.
 * Contains the predefined measument types for our ingredient units.
 */
public enum UnitType {
    PIECE ("per Piece"),
    MILLILITER("Milliliters"),
    GRAM("Grams"),
    TEASPOON("per Teaspoon");

    private final String displayOfUnit;

    UnitType(String displayOfUnit) {
        this.displayOfUnit = displayOfUnit;
    }

public String getDisplayOfUnit() {
        return displayOfUnit;
}



}
