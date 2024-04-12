package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public interface RecipeBookRepository extends JpaRepository<RecipeBook, Long> {
    Optional<RecipeBook> findByRecipeBookName(String RecipeBookName);
}
