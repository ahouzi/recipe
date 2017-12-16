package com.recipe.project.controllers;

import com.recipe.project.domain.Category;
import com.recipe.project.domain.Recipe;
import com.recipe.project.repositories.CategoryRepository;
import com.recipe.project.repositories.UnitOfMeasureRepository;
import com.recipe.project.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by aah on 21/11/17.
 */
@Controller
public class IndexController {

    @Autowired
    private  RecipeService recipeService;

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());

        return "index";
    }



}