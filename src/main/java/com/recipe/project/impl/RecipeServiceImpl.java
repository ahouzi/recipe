package com.recipe.project.impl;

import com.recipe.project.domain.Recipe;
import com.recipe.project.repositories.RecipeRepository;
import com.recipe.project.services.RecipeService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet :: add);

        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long id) {

       Optional<Recipe> recipeOptional = recipeRepository.findById(id);

       if (!recipeOptional.isPresent()){
           throw new RuntimeException("recipe not found");
       }

        return recipeOptional.get() ;
    }
}
