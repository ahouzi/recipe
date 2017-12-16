package com.recipe.project.impl;

import com.recipe.project.domain.Recipe;
import com.recipe.project.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by aah on 27/11/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {


    @InjectMocks
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        recipeService = new RecipeServiceImpl();
    }

    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

       when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
    }


    @Test
    public void getRecipeById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);


        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe result = recipeService.getRecipeById(1L);

        assertEquals(Long.valueOf(1L),result.getId());

        assertNotNull("null value returned",result);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }




}