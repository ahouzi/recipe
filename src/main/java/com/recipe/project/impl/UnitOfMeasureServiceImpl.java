package com.recipe.project.impl;

import com.recipe.project.commands.UnitOfMeasureCommand;
import com.recipe.project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.recipe.project.domain.UnitOfMeasure;
import com.recipe.project.repositories.UnitOfMeasureRepository;
import com.recipe.project.services.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



/**
 * Created by aah on 17/12/17.
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {


    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

//        Set<UnitOfMeasure> list = new HashSet<UnitOfMeasure>();
//
//        unitOfMeasureRepository.findAll().iterator().forEachRemaining(list::add);
//
//        list.stream().map( unitOfMeasureToUnitOfMeasureCommand :: convert).collect(Collectors.toSet());

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false).map(unitOfMeasureToUnitOfMeasureCommand::convert).
                collect(Collectors.toSet());


    }
}
