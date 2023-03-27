package com.banurns.skladbanurnsrest.service;

import com.banurns.skladbanurnsrest.model.Store;

import java.util.List;

public interface StoreService {
    Store addStore(Store store);
    void deleteStore(Long id);

    List<Store> getallStores();

    Store getStoreById(Long id);

}
