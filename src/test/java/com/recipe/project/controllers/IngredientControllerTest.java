package com.recipe.project.controllers;

import com.recipe.project.commands.IngredientCommand;
import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.domain.Recipe;
import com.recipe.project.services.IngredientService;
import com.recipe.project.services.RecipeService;
import com.recipe.project.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by aah on 16/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {


    @Mock
    RecipeService recipeService;
    @Mock
    private UnitOfMeasureService unitOfMeasureService;
    @Mock
    IngredientService ingredientService;
    @Mock
    Model model;
    @InjectMocks
    IngredientController controller ;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void listIngredients() throws Exception {

        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.getRecipeCommandById(anyString())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredientList"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).getRecipeCommandById(anyString());
    }


    @Test
    public void testShowIngredient() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }


    @Test
    public void testNewIngredientForm() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");

        //when
        when(recipeService.getRecipeCommandById(anyString())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).getRecipeCommandById(anyString());

    }




}