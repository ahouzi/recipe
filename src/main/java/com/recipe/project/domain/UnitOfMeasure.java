package com.recipe.project.domain;

import lombok.Getter;
import lombok.Setter;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


/**
 * Created by aah on 25/11/17.
 */
@Getter
@Setter
@Document
public class UnitOfMeasure {

    @Id
    private String id;
    private String uom;
}