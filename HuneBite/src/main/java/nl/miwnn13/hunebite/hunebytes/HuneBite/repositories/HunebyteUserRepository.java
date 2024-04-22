package nl.miwnn13.hunebite.hunebytes.HuneBite.repositories;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.HunebyteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
public interface HunebyteUserRepository extends JpaRepository<HunebyteUser, Long> {
    Optional<HunebyteUser> findByUsername(String username);
}
