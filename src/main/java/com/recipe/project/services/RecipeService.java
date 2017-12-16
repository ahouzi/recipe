package com.recipe.project.services;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.domain.Recipe;

import java.util.Optional;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand );


}
