package com.recipe.project.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aah on 26/11/17.
 */
@Getter
@Setter
@Document
public class Category {
    @Id
    private String id;
    private String description;

    @DBRef
    private Set<Recipe> recipes;
}