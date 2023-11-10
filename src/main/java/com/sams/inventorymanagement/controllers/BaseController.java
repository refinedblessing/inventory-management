package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.UserRole;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.AppUserService;
import com.sams.inventorymanagement.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class BaseController {

    @Autowired
    private AppUserService appUserService;

    protected AppUser getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser user = appUserService.getUserById(userDetails.getId());

        if (user == null) {
            throw new EntityNotFoundException("User not found with ID: " + userDetails.getId());
        }

        return user;
    }

    protected boolean isAdmin() {
        AppUser user = getCurrentUser();
        return user.getRoles().contains(UserRole.ROLE_ADMIN);
    }

    protected boolean isNotAdmin() {
        return !isAdmin();
    }

    protected boolean isManager() {
        AppUser user = getCurrentUser();
        return user.getRoles().contains(UserRole.ROLE_STORE_MANAGER);
    }

    protected boolean isStoreStaff(Store currStore) {
        Optional<Store> storeWithId = getCurrentUser().getStores().stream()
                .filter(store -> store.getId().equals(currStore.getId()))
                .findFirst();
        return storeWithId.isPresent() || isAdmin();
    }

    protected boolean isNotStoreStaff(Store currStore) {
        return !isStoreStaff(currStore);
    }
}
