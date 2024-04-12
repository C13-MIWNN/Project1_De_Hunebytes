package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Handles everything related to ingredients
 **/

@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
// voor je overzicht
    @GetMapping("/ingredient")
    private String ShowAllIngredients(Model model) {
       model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("newIngredient", new Ingredient());

    return "ingredientOverview";
    }
    // dit is voor je form weergeven
    @GetMapping("/ingredient/new")
    private String showIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredientForm";
    }
// dit is voor je form opslaan
    @PostMapping("/ingredient/new")
   private String AddNewIngredient(@ModelAttribute("ingredient")
                                       Ingredient ingredient, BindingResult ingredientresult) {

        if (!ingredientresult.hasErrors()) {
            ingredientRepository.save(ingredient);
        }
// deze redirect is niet logisch? je wilt eigenlijk naar detail aangemaakte ingredient? nee niet perse.
        return "redirect:/ingredient";
    }
// voor je detail pagina
    @GetMapping("/ingredient/detail/{ingredientName}")
    private String showIngredientDetails(@PathVariable("ingredientName") String ingredientName, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);

        if (ingredient.isEmpty()) {
        return "redirect:/";
        }

        model.addAttribute("ingredientToBeShown", ingredient.get());
        return "ingredientDetail";
    }
}
