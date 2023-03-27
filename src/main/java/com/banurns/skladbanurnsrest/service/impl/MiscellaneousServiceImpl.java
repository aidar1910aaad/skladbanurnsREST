package com.banurns.skladbanurnsrest.service.impl;

import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.repository.MiscellaneousRepository;
import com.banurns.skladbanurnsrest.service.MiscellaneousService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MiscellaneousServiceImpl implements MiscellaneousService {

    private final MiscellaneousRepository miscellaneousRepository;
    @Autowired
    public MiscellaneousServiceImpl(MiscellaneousRepository miscellaneousRepository) {
        this.miscellaneousRepository = miscellaneousRepository;
    }

    @Override
    public Miscellaneous addMisc(Miscellaneous misc) {
        misc.setStatus(Status.ACTIVE);
        Miscellaneous savedMisc = miscellaneousRepository.save(misc);
        log.info("used adding of miscellaneous");
        return savedMisc;
    }

    @Override
    public Miscellaneous control(Long id , Long quantity){
        Miscellaneous miscellaneous = miscellaneousRepository.findById(id).orElse(null);
        if (miscellaneous == null) {
            return null;
        }
        Long dbq = miscellaneous.getQuantity();
        if (dbq + quantity < 0) { return null; }
        miscellaneous.setQuantity(dbq + quantity);
        Miscellaneous savedMisc = miscellaneousRepository.save(miscellaneous);
        return savedMisc;
    }

    @Override
    public List<Miscellaneous> getMiscs() {
        List<Miscellaneous> miscs = miscellaneousRepository.findAll();
        log.info("list of miscellaneouses was fetched");
        return miscs;
    }

    @Override
    public List<Miscellaneous> getActiveMiscs() {
        List<Miscellaneous> miscs = miscellaneousRepository.findAllByStatus(Status.ACTIVE);
        log.info("list of active miscellanes was fetched");
        return miscs;
    }

    @Override
    public Miscellaneous EnableMiscById(Long id) {
        Miscellaneous misc = miscellaneousRepository.findById(id).orElse(null);
        if (misc == null) {
            log.warn("Could not find miscellane");
            return null;
        }
        misc.setStatus(Status.ACTIVE);
        Miscellaneous savedmisc = miscellaneousRepository.save(misc);
        return savedmisc;
    }

    @Override
    public Miscellaneous disableMiscById(Long id) {
        Miscellaneous misc = miscellaneousRepository.findById(id).orElse(null);
        if (misc == null) {
            log.warn("Could not find miscellane");
            return null;
        }
        misc.setStatus(Status.NOT_ACTIVE);
        Miscellaneous savedmisc = miscellaneousRepository.save(misc);
        return savedmisc;
    }

    @Override
    public void removeMiscById(Long id) {
        miscellaneousRepository.deleteById(id);
        log.info("miscellaneous was successfully removed" + id);
    }
}
