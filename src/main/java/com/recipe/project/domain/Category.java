package com.recipe.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
@Data
public class Category {

    private String id;

    private String description;
    private Set<Recipe> recipes;

}
