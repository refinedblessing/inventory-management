package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.enums.UserRole;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.AppUserService;
import com.sams.inventorymanagement.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

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

    protected boolean isManager() {
        AppUser user = getCurrentUser();
        return user.getRoles().contains(UserRole.ROLE_STORE_MANAGER);
    }
}
