package com.recipe.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by aah on 25/11/17.
 */
@Data
public class Notes {

    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
