package com.recipe.project.controllers;

import com.recipe.project.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aah on 16/12/17.
 */
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/recipe/show/{id}")
    public String getRecipe(Model model, @PathVariable String id ) {

        model.addAttribute("recipe",recipeService.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }

}
