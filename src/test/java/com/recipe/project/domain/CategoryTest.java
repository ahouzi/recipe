package com.recipe.project.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aah on 27/11/17.
 */
public class CategoryTest {

    private Category category;

    @Before
    public void setUp(){
        category= new Category();
    }
    @Test
    public void setId() throws Exception {
        String aLong= "4";
        category.setId(aLong);
        assertEquals(aLong,category.getId());
    }

    @Test
    public void setDescription() throws Exception {
    }

    @Test
    public void setRecipes() throws Exception {
    }

}