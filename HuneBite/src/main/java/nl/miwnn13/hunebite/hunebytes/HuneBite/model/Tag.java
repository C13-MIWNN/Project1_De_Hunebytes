package nl.miwnn13.hunebite.hunebytes.HuneBite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Entity
public class Tag {
    @Id @GeneratedValue
    private Long tagId;
}
