package com.recipe.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by aah on 25/11/17.
 */

public class Notes {

    @Id
    private String id;
    private String recipeNotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}