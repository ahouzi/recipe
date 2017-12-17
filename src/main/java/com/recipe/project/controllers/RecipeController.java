package com.recipe.project.controllers;

import com.recipe.project.commands.RecipeCommand;
import com.recipe.project.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aah on 16/12/17.
 */
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    //this is optimal
    @GetMapping("/recipe/{id}/show")
    public String getRecipe(Model model, @PathVariable String id ) {
        model.addAttribute("recipe",recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    //this is not optimal
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


    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(id)));

        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        recipeService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(Long.valueOf(id));

        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }



}
