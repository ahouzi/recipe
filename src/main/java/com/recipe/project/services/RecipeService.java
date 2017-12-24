package com.recipe.project.services;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.domain.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(String  id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand );

    RecipeCommand getRecipeCommandById(String aLong);

    void deleteById(String idToDelete);

    void saveImageFile(String id, MultipartFile multipartFile);
}
