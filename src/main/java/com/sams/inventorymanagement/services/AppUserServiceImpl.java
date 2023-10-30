package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.exceptions.ValidationException;
import com.sams.inventorymanagement.repositories.AppUserRepository;
import com.sams.inventorymanagement.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing application users (AppUser entities).
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public AppUser createUser(AppUser user) {
        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new EntityDuplicateException("Email already taken.");
        }

        if (appUserRepository.existsByUsername(user.getUsername())) {
            throw new EntityDuplicateException("Username already taken.");
        }

        try {
            return appUserRepository.save(user);
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getMessage());
        }

    }

    @Override
    public AppUser updateUser(Long id, AppUser updatedUser) {
        if (appUserRepository.existsById(id)) {
            updatedUser.setId(id); // Ensure the ID is set
            return appUserRepository.save(updatedUser);
        } else {
            return null; // Return null if the user with the specified ID does not exist
        }
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email).orElse(null);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) { return appUserRepository.existsByEmail(email); }

    @Override
    public boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }

    @Override
    public void addStoreToUser(Long userId, Long storeId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        user.getStores().add(store);
        store.getUsers().add(user);

        appUserRepository.save(user);
        storeRepository.save(store);
    }

    @Transactional
    @Override
    public void removeStoreFromUser(Long userId, Long storeId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        if (user.getStores().contains(store)) {
            user.getStores().remove(store);
            store.getUsers().remove(user);

            appUserRepository.save(user);
            storeRepository.save(store);
        } else {
            throw new EntityNotFoundException("Store not found for user");
        }
    }

    @Override
    public void addAllStoresToUser(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        List<Store> allStores = storeRepository.findAll();

        user.getStores().addAll(allStores);  // Add all stores to the user

        for (Store store : allStores) {
            store.getUsers().add(user);  // Add the user to each store
        }

        appUserRepository.save(user);
        storeRepository.saveAll(allStores);
    }

    @Transactional
    @Override
    public void removeAllStoresFromUser(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        for (Store store : user.getStores()) {
            store.getUsers().remove(user);  // Remove the user from each store
        }

        user.getStores().clear();  // Clear all stores from the user

        appUserRepository.save(user);
    }
}
