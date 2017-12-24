package com.recipe.project.domain;

import com.recipe.project.domain.type.Difficulty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aah on 25/11/17.
 */
@Getter
@Setter
public class Recipe {


    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    Set<Ingredient> ingredientList=new HashSet<>();;
    private Difficulty difficulty;
    private Byte[] image;
    private Notes notes;

    private Set<Category> categories= new HashSet<>();;

    public void setNotes(Notes notes) {
        this.notes = notes;
        if (notes != null) {
            notes.setRecipe(this);
        }
    }
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredientList.add(ingredient);
        return this;
    }
}
