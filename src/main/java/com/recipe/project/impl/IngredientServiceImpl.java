package com.recipe.project.impl;

import com.recipe.project.commands.IngredientCommand;
import com.recipe.project.converters.IngredientCommandToIngredient;
import com.recipe.project.converters.IngredientToIngredientCommand;
import com.recipe.project.converters.RecipeCommandToRecipe;
import com.recipe.project.converters.RecipeToRecipeCommand;
import com.recipe.project.domain.Ingredient;
import com.recipe.project.domain.Recipe;
import com.recipe.project.repositories.RecipeRepository;
import com.recipe.project.repositories.UnitOfMeasureRepository;
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
    @Autowired
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

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

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {

            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredientList().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()) ).findFirst();

            if (ingredientOptional.isPresent()){

               Ingredient ingredientFound = ingredientOptional.get();
               ingredientFound.setAmount(command.getAmount());
               ingredientFound.setDescription(command.getDescription());
               ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId()).orElseThrow(
                       () -> new RuntimeException("UOM not found")
               ));


            }else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);

                recipe.addIngredient(ingredient);

            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredientList().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredientList().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }

    }
}