package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Justin Lamberts
 * Handles everything realted to ingredients
 **/

@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredient")
    private String ShowAllIngredients(Model model) {
       model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("newIngredient", new Ingredient());

    return "ingredientOverview";
    }

@PostMapping("/ingredient/new")
   private String AddNewIngredient(@ModelAttribute("newIngredient")
                                   Ingredient ingredient,
                                   BindingResult ingredientresult) {
    if (!ingredientresult.hasErrors()) {
        ingredientRepository.save(ingredient);
    }
    return "redirect:/ingredient";
}


}
