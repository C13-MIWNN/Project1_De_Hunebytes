package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public interface BookRepository extends JpaRepository<RecipeBook, Long> {
}
