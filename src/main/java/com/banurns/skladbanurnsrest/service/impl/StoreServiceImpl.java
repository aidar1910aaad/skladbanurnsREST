package com.banurns.skladbanurnsrest.service.impl;

import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.model.Store;
import com.banurns.skladbanurnsrest.repository.StoresRepository;
import com.banurns.skladbanurnsrest.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoresRepository storesRepository;
    @Autowired
    public StoreServiceImpl(StoresRepository storesRepository){
        this.storesRepository = storesRepository;
    }
    @Override
    public Store addStore(Store store) {
        store.setStatus(Status.ACTIVE);
        Store savedStore = storesRepository.save(store);
        log.info("Inserted store");
        return savedStore;
    }

    @Override
    public void deleteStore(Long id) {
        storesRepository.deleteById(id);
        log.info("Deleted store");
    }

    @Override
    public List<Store> getallStores() {
        List<Store> stores = storesRepository.findAll();
        return stores;
    }

    @Override
    public Store getStoreById(Long id) {
        Store store = storesRepository.findById(id).orElse(null);
        return store;
    }


}
