package nl.miwnn13.hunebite.hunebytes.HuneBite.controller;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.Recipe;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Controller
public class SearchController {
    private final RecipeRepository recipeRepository;

    public SearchController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/search")
    public String searchHunebytesByQuery(@RequestParam("search") String searchQuery, Model model){
        List<Recipe> searchResults = recipeRepository.findAllByRecipeTitleContaining(searchQuery);
        model.addAttribute("searchResults", searchResults);
        return "searchResult";
    }
}
