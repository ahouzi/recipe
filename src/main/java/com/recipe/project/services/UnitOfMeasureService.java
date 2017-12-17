package com.recipe.project.services;

import com.recipe.project.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by aah on 17/12/17.
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
