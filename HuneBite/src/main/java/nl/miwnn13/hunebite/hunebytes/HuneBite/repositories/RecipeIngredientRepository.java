package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
