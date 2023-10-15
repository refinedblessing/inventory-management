package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;

import java.time.LocalDate;
import java.util.List;

public interface StoreService {
    List<Store> searchStoresByCriteria(
            String name,
            String address,
            StoreType storeType,
            LocalDate openingDate
    );

    Store getStoreById(Long id);

    Store getStoreByName(String name);

    Store createStore(Store item);

    void deleteStore(Long id);

    Store updateStore(Long id, Store updatedStore);

    List<Store> getAllStores();
}
