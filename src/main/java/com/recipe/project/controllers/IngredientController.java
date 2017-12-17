package com.recipe.project.controllers;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aah on 16/12/17.
 */
@Slf4j
@Controller
public class IngredientController {

    @Autowired
    private RecipeService recipeService;



    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }



}
