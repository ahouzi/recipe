package com.recipe.project.controllers;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aah on 16/12/17.
 */
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String getRecipe(Model model, @PathVariable String id ) {
        model.addAttribute("recipe",recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipeForm", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable String id ) {
        model.addAttribute("recipeForm",recipeService.getRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id ) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }



    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() +  "/show" ;
    }




}
