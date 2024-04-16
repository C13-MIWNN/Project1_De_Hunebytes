package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Ingredient;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeBookRepository;
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
 * Purpose for the class
 **/
@Controller
public class RecipeBookController {
    private final RecipeBookRepository recipeBookRepository;

    public RecipeBookController(RecipeBookRepository recipeBookRepository) {
        this.recipeBookRepository = recipeBookRepository;
    }

    @GetMapping({"/"})
    public String showOverviewPage(Model model) {
        model.addAttribute("allRecipeBooks", recipeBookRepository.findAll());
        return "homepageOverview";
    }

    @GetMapping("/recipebook/new")
    private String showIngredientForm(Model model) {
        model.addAttribute("NewRecipeBook", new RecipeBook());
        return "RecipeBookForm";
    }

    @PostMapping("/recipebook/new")
    private String addRecipeBook(@ModelAttribute("NewRecipeBook") RecipeBook recipeBookToBeSaved, BindingResult result) {
        if (recipeBookToBeSaved.getRecipeBookId() == null
                && recipeBookRepository.findByRecipeBookName(recipeBookToBeSaved.getRecipeBookName()).isPresent()) {
            return "redirect:/recipebook/new";
        }

        if (!result.hasErrors()) {
            recipeBookRepository.save(recipeBookToBeSaved);
        }

        String recipeBookName = recipeBookToBeSaved.getRecipeBookName();

        return "redirect:/recipebook/detail/" + recipeBookName;
    }
    @GetMapping("/recipebook/detail/{RecipeBook}")
    private String showRecipeBookDetail(@PathVariable("RecipeBook") String RecipeBook, Model model) {
        Optional<RecipeBook> recipeBook = recipeBookRepository.findByRecipeBookName(RecipeBook);

        if (recipeBook.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeBookToBeShown", recipeBook.get());
        return "RecipeBookOverviewPage";
    }
}
 