package kpc.springframework.recipe.services;

import kpc.springframework.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService {
    Set<Recipe> getRecipies();
}
