package com.banurns.skladbanurnsrest.service;

import com.banurns.skladbanurnsrest.model.Flavor;
import org.hibernate.metamodel.mapping.ordering.ast.FkDomainPathContinuation;

import java.util.List;

public interface FlavorService {

    Flavor addFlavor(Flavor flavor);

    List<Flavor> getFlavors();

    List<Flavor> getActiveFlavors();

    Flavor EnableFlavorById(Long id);

    Flavor disableFlavorById(Long id);

    void removeFlavorById(Long id);

    Flavor control(Long id , Long quantity);

}
