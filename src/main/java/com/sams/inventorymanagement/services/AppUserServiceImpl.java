package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.dto.AppUserDTO;
import com.sams.inventorymanagement.dto.StoreDTO;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.exceptions.InvalidStatusTransitionException;
import com.sams.inventorymanagement.exceptions.ValidationException;
import com.sams.inventorymanagement.repositories.AppUserRepository;
import com.sams.inventorymanagement.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Transactional
    @Override
    public AppUser updateUser(AppUser admin, AppUserDTO updatedUser) {
        AppUser user = appUserRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

//        TODO include SUPER_ADMIN User that can update ADMIN
//        TODO update Exception handler

//        Stopping Admin from sending updates to other ADMIN users
        if (user.isAdmin() && !(user.getId().equals(admin.getId()))) {
            throw new InvalidStatusTransitionException("Unauthorised to update an ADMIN");
        }

//            Stopping Admin from creating ADMINS
//        check if the update includes an admin role, and confirm that it is not the current admin before proceeding
        if (updatedUser.isAdmin() && !(admin.getId().equals(updatedUser.getId()))) {
            throw new InvalidStatusTransitionException("Unauthorised to create another ADMIN");
        }

//            Stopping Admin from removing ADMIN role from themselves like I just did :(
        if (user.isAdmin() && !(updatedUser.isAdmin())) {
            throw new InvalidStatusTransitionException("Unauthorised to remove another ADMIN");
        }

//        Finally do the updates
//        update user roles
        user.setRoles(updatedUser.getRoles());

//        clear out the old store info
        for (Store store : user.getStores()) {
            store.getUsers().remove(user);
        }
        user.getStores().clear();

//        create empty stores list
        List<Store> stores = new ArrayList<>();

        // Add all stores to the user and vice-versa
        for (StoreDTO s : updatedUser.getStores()) {
            Store store = storeRepository.findById(s.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + s.getId()));

            user.getStores().add(store);
            store.getUsers().add(user);

//            update stores list
            stores.add(store);
        }

//        save all stores
        storeRepository.saveAll(stores);
        return appUserRepository.save(user);
    }
}
