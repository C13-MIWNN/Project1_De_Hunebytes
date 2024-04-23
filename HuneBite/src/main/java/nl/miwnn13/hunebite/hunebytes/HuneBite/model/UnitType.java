package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

/**
 * @author Mareth Westhoff.
 * Contains the predefined measument types for our ingredient units.
 */
public enum UnitType {
    PIECE ("per Piece", ""),
    MILLILITER("per Milliliter", "Milliliters"),
    GRAM("per Gram", "Grams"),
    TEASPOON("per Teaspoon", "Teaspoons"),;

    private final String displayOfUnit;
    private final String displayTotal;

    UnitType(String displayOfUnit, String displayTotal ) {
        this.displayOfUnit = displayOfUnit;
        this.displayTotal = displayTotal;
    }

public String getDisplayOfUnit() {
        return displayOfUnit;
}

public String getDisplayTotal() {
        return displayTotal;
}


}
