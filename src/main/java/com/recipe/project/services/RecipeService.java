package com.recipe.project.services;

import com.recipe.project.domain.Recipe;

import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();
}
