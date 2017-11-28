package com.recipe.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
