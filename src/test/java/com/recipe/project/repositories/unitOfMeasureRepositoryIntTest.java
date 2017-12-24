package com.recipe.project.repositories;

import com.recipe.project.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by aah on 27/11/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class unitOfMeasureRepositoryIntTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUom() throws Exception {

        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Pinch");

        assertEquals("Pinch", unitOfMeasure.get().getUom());

    }

}