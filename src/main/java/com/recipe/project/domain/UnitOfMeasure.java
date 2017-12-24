package com.recipe.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aah on 25/11/17.
 */
@Getter
@Setter
public class UnitOfMeasure {

    private String  id;
    private String uom;

}
