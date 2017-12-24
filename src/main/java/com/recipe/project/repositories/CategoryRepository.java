package com.recipe.project.repositories;

import com.recipe.project.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by aah on 26/11/17.
 */
public interface CategoryRepository extends CrudRepository<Category,String> {


    Optional<Category> findByDescription(String description);
}
