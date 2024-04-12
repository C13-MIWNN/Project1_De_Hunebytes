package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeBookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("RecipeBook", recipeBookRepository.findAll());
        return "homepageOverview";
    }
    @GetMapping("/recipeBook/detail/{SpecificRecipeBook}")
    private String showRecipeDetail(@PathVariable("SpecificRecipeBook") String SpecificRecipeBook, Model model) {
        Optional<RecipeBook> recipeBookName = recipeBookRepository.findByRecipeBookName(SpecificRecipeBook);

        if (SpecificRecipeBook.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeBookToBeShown", recipeBookName.get().getRecipeBookName());
        return "RecipeBookOverviewPage";
    }
}
 