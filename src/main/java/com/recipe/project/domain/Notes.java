package com.recipe.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by aah on 25/11/17.
 */
@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
