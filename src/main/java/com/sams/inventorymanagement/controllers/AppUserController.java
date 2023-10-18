package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.services.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing application users (AppUser entities).
 */
@RestController
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    /**
     * Constructs a new AppUserController with the provided AppUserService.
     *
     * @param appUserService The service responsible for user-related operations.
     */
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Get a list of all users.
     *
     * @return A ResponseEntity containing a list of AppUser entities with an HTTP status of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A ResponseEntity containing the requested AppUser with an HTTP status of OK (200) if found,
     * or an HTTP status of NOT_FOUND (404) if the user is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = appUserService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new user.
     *
     * @param user The user to be created.
     * @return A ResponseEntity containing the created AppUser with an HTTP status of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody AppUser user) {
        AppUser createdUser = appUserService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Update an existing user.
     *
     * @param id         The unique identifier of the user to be updated.
     * @param updatedUser The updated user entity.
     * @return A ResponseEntity containing the updated AppUser with an HTTP status of OK (200) if found,
     * or an HTTP status of NOT_FOUND (404) if the user is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @Valid @RequestBody AppUser updatedUser) {
        AppUser user = appUserService.updateUser(id, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return A ResponseEntity with an HTTP status of NO_CONTENT (204) indicating a successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
