package com.recipe.project.services;

import com.recipe.project.commands.IngredientCommand;

/**
 * Created by aah on 17/12/17.
 */
public interface IngredientService {


    IngredientCommand findByRecipeIdAndIngredientId(String  l, String l1);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
