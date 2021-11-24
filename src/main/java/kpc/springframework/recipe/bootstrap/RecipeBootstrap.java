package kpc.springframework.recipe.bootstrap;

import kpc.springframework.recipe.domain.*;
import kpc.springframework.recipe.repositories.CategoryRepository;
import kpc.springframework.recipe.repositories.RecipeRepository;
import kpc.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }
        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }
        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }
        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }
        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (!italianCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }
        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if (!fastFoodCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category fastFoodCategory = fastFoodCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1.Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2.Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3.Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4.Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.\n" +
                "Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);


        guacRecipe.getIngredients().add(new Ingredient("ripe avocados",new BigDecimal(2),eachUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("salt",new BigDecimal(".25"),teaspoonUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice",new BigDecimal(1),tablespoonUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(4),tablespoonUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced",new BigDecimal(2),eachUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),tablespoonUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("freshly ground black pepper",new BigDecimal(1),pinchUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, chopped (optional)",new BigDecimal(".5"),eachUom,guacRecipe));

        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.getCategories().add(americanCategory);
        recipes.add(guacRecipe);

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(15);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1.Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "2.Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3.Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4.Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5.Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
                );
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacoNotes.setRecipe(tacoRecipe);
        tacoRecipe.setNotes(tacoNotes);


        tacoRecipe.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal(2),tablespoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1),teaspoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("dried cumin", new BigDecimal(1),teaspoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1),teaspoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(".5"),teaspoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("clove garlic, finely chopped", new BigDecimal(".5"),eachUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1),tablespoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),tablespoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2),tablespoonUom,tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("kinless, boneless chicken thighs", new BigDecimal(5),eachUom,tacoRecipe));

        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);



        return recipes;
    }
}
