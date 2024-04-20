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


    @PostMapping("/ingredient/new")
    private String AddNewIngredient(@ModelAttribute("ingredient")
                                    Ingredient ingredient, BindingResult ingredientResult, Model model) {

        if (ingredient.getIngredientId() == null
                && ingredientRepository.findByIngredientName(ingredient.getIngredientName()).isPresent()) {
            ingredientResult.rejectValue("ingredientName", "ingredient.exists",
                    "Ingredient already exists");
            model.addAttribute("ingredient", ingredient);

            return "ingredientForm";
        }

        if (!ingredientResult.hasErrors()) {
            ingredientRepository.save(ingredient);
        }
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
// pas je ingredient aan als deze niet empty is, via ingredient form.
    @GetMapping("/ingredient/edit/{ingredientName}")
    private String showIngredientEditForm(@PathVariable("ingredientName") String ingredientName, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);

        if (ingredient.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("ingredient", ingredient.get());

        return "ingredientForm";
    }
}
