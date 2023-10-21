package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.exceptions.ValidationException;
import com.sams.inventorymanagement.repositories.AppUserRepository;
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
}
