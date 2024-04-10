package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
