package com.recipe.project.services;

import com.recipe.project.commands.IngredientCommand;

/**
 * Created by aah on 17/12/17.
 */
public interface IngredientService {


    IngredientCommand findByRecipeIdAndIngredientId(long l, long l1);
}
