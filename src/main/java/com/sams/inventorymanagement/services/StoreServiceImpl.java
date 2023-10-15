package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;
    
    @Override
    public List<Store> searchStoresByCriteria(String name, String address, StoreType storeType, LocalDate openingDate) {
        return storeRepository.searchStoresByCriteria(name, address, storeType, openingDate);
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public Store getStoreByName(String name) {
        return storeRepository.findByName(name).orElse(null);
    }

    @Override
    public Store createStore(Store store) {
        if (storeRepository.findByName(store.getName()).isPresent()) {
            throw new EntityDuplicateException("Store with name '" + store.getName() + "' already exists.");
        }

        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Store updateStore(Long id, Store updatedStore) {
        if (storeRepository.existsById(id)) {
            updatedStore.setId(id); // Ensure the ID is set
            return storeRepository.save(updatedStore);
        } else {
            return null; // Return null if the store with the specified ID does not exist
        }
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}
