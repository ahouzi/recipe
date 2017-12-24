package com.recipe.project.impl;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.converters.RecipeCommandToRecipe;
import com.recipe.project.converters.RecipeToRecipeCommand;
import com.recipe.project.domain.Recipe;
import com.recipe.project.exceptions.NotFoundException;
import com.recipe.project.repositories.RecipeRepository;
import com.recipe.project.services.RecipeService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
@Slf4j
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet :: add);

        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(String id) {

       Optional<Recipe> recipeOptional = recipeRepository.findById(id);

       if (!recipeOptional.isPresent()){
           throw new NotFoundException("recipe not found");
       }

        return recipeOptional.get() ;
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("saved Recipe: ", savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand getRecipeCommandById(String  aLong) {
        return recipeToRecipeCommand.convert(getRecipeById(aLong));
    }

    @Override
    public void deleteById(String  idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }



    @Override
    public void saveImageFile(String recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
        }
    }


}
