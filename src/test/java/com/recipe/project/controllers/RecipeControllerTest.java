package com.recipe.project.controllers;

import com.recipe.project.domain.Recipe;
import com.recipe.project.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by aah on 16/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    Model model;


    @InjectMocks
    RecipeController recipeController ;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }


    @Test
    public void getRecipe() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1")).andExpect(status().isOk()).
                andExpect(view().name("recipe/show")).andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

    }
}