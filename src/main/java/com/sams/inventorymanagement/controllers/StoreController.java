package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    
    @GetMapping
    public List<Store> searchStores(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) StoreType storeType,
            @RequestParam(required = false) LocalDate openingDate
    ) {
        return storeService.searchStoresByCriteria(name, address, storeType, openingDate);
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);

        if(store == null)
            throw new EntityNotFoundException("id: " + id);

        return store;
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@Valid @RequestBody Store store) {
        Store savedStore = storeService.createStore(store);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStore.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @Valid @RequestBody Store updatedStore) {
        return storeService.updateStore(id, updatedStore);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
