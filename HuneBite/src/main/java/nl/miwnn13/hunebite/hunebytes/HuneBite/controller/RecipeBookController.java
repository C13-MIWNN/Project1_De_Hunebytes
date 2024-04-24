package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.RecipeBook;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeBookRepository;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
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
 * Handles
 **/
@Controller
public class RecipeBookController {
    private final RecipeBookRepository recipeBookRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBookController(RecipeBookRepository recipeBookRepository, RecipeRepository recipeRepository) {
        this.recipeBookRepository = recipeBookRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/"})
    public String showOverviewPage(Model model, RecipeBook recipeBook) {
        model.addAttribute("allRecipeBooks", recipeBookRepository.findAll());
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "homepageOverview";
    }

    @GetMapping("/recipebook/new")
    private String showIngredientForm(Model model) {
        model.addAttribute("NewRecipeBook", new RecipeBook());
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeBookForm";
    }

    @PostMapping("/recipebook/new")
    private String addRecipeBook(@ModelAttribute("NewRecipeBook") RecipeBook recipeBookToBeSaved, BindingResult result){
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
        model.addAttribute("allRecipes", recipeRepository.findAll());
        model.addAttribute("recipeBookToBeShown", recipeBook.get());

        return "recipeBookOverviewPage";
    }

    @PostMapping("/recipebook/detail/{RecipeBook}")
    private String addRecipeBookDetail(@ModelAttribute("addRecipeToBook")
                                           RecipeBook recipeBookToBeSaved, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            recipeBookRepository.save(recipeBookToBeSaved);
        }


        return "recipeBookOverviewPage";
    }


}
 