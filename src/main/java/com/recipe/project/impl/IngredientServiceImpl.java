package com.recipe.project.impl;

import com.recipe.project.commands.IngredientCommand;
import com.recipe.project.converters.IngredientToIngredientCommand;
import com.recipe.project.converters.RecipeCommandToRecipe;
import com.recipe.project.converters.RecipeToRecipeCommand;
import com.recipe.project.domain.Recipe;
import com.recipe.project.repositories.RecipeRepository;
import com.recipe.project.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by aah on 17/12/17.
 */
@Slf4j
@Service
@Transactional
public class IngredientServiceImpl  implements IngredientService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //TODO impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();


        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredientList().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        if(!ingredientCommandOptional.isPresent()){
            //TODO impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
