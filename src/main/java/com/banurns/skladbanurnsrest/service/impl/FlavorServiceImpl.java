package com.banurns.skladbanurnsrest.service.impl;

import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.repository.FlavorsRepository;
import com.banurns.skladbanurnsrest.service.FlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FlavorServiceImpl implements FlavorService {

    private final FlavorsRepository flavorRepository;

    @Autowired
    public FlavorServiceImpl(FlavorsRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    @Override
    public Flavor addFlavor(Flavor flavor) {
        flavor.setStatus(Status.ACTIVE);
        Flavor flav = flavorRepository.save(flavor);
        return flav;
    }

    @Override
    public List<Flavor> getFlavors() {
        List<Flavor> flavors = flavorRepository.findAll();
        log.info("fetching list of flavors");
        return flavors;
    }

    @Override public Flavor control(Long id , Long quantity){
        Flavor fl = flavorRepository.findById(id).orElse(null);
        if (fl == null) {return null;}
        Long dbq = fl.getQuantity();
        if (dbq + quantity < 0) {return null;}
        fl.setQuantity(dbq + quantity);
        Flavor savedFlavor = flavorRepository.save(fl);
        return savedFlavor;
    }

    @Override
    public List<Flavor> getActiveFlavors() {
        List<Flavor> flavors = flavorRepository.findAllByStatus(Status.ACTIVE);
        log.info("fetching list of active flavors");
        return flavors;
    }

    @Override
    public Flavor EnableFlavorById(Long id) {
        Flavor flavor = flavorRepository.findById(id).orElse(null);
        if (flavor == null) {
            log.warn("Tried to enable flavor , but it doesnt exist");
            return null;
        }

        flavor.setStatus(Status.ACTIVE);
        Flavor flav = flavorRepository.save(flavor);
        return flav;
    }

    @Override
    public Flavor disableFlavorById(Long id) {
        Flavor flavor = flavorRepository.findById(id).orElse(null);
        if (flavor == null) {
            log.warn("Tried to disable flavor , but it doesnt exist");
            return null;
        }

        flavor.setStatus(Status.NOT_ACTIVE);
        Flavor flav = flavorRepository.save(flavor);
        return flav;
    }

    @Override
    public void removeFlavorById(Long id) {
        flavorRepository.deleteById(id);
        log.info("Flavor deleted" + id);
    }
}
