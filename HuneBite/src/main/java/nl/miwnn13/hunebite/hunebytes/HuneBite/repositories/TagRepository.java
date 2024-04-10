package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public interface TagRepository extends JpaRepository<Tag, Long> {
}
