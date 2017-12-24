package com.recipe.project.repositories;

import com.recipe.project.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by aah on 26/11/17.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,String> {

    Optional<UnitOfMeasure> findByUom(String uom);
}
