package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface recipeRepository extends JpaRepository<Recipe, Long> {
}
