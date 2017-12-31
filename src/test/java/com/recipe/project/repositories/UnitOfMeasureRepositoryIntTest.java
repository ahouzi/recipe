package com.recipe.project.repositories;


import com.recipe.project.bootstrap.RecipeBootstrap;
import com.recipe.project.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


/**
 * Created by aah on 27/11/17.
 */

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIntTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RecipeRepository recipeRepository;

    RecipeBootstrap recipeBootstrap;
    @Before
    public void setUp() throws Exception {
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();
        recipeBootstrap = new RecipeBootstrap(categoryRepository,recipeRepository,unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() throws Exception {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getUom());
    }

    @Test
    public void findByDescriptionCup() throws Exception {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", uomOptional.get().getUom());
    }

}


