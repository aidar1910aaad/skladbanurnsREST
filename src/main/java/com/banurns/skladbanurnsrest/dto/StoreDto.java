package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.model.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreDto {
    private Long id;
    private String name;
    private Status status;

    public Store toStore(){
        Store store = new Store();
        store.setStatus(status);
        store.setName(name);
        setId(store.getId());
        return store;
    }

    public static StoreDto toDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setName(store.getName());
        storeDto.setStatus(store.getStatus());
        return storeDto;
    }
}
