package com.recipe.project.repositories;

import com.recipe.project.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aah on 26/11/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
