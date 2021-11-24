package kpc.springframework.recipe.controllers;

import kpc.springframework.recipe.domain.Category;
import kpc.springframework.recipe.domain.UnitOfMeasure;
import kpc.springframework.recipe.repositories.CategoryRepository;
import kpc.springframework.recipe.repositories.UnitOfMeasureRepository;
import kpc.springframework.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes",recipeService.getRecipies());
        return "index";
    }
}
