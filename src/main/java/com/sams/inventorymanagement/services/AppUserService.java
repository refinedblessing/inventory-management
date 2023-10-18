package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.AppUser;

import java.util.List;

/**
 * Service interface for managing application users (AppUser entities).
 */
public interface AppUserService {
    /**
     * Get a list of all users.
     *
     * @return A list of AppUser entities.
     */
    List<AppUser> getAllUsers();

    /**
     * Get a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return The requested AppUser, or null if not found.
     */
    AppUser getUserById(Long id);

    /**
     * Create a new user.
     *
     * @param user The user to be created.
     * @return The created AppUser.
     */
    AppUser createUser(AppUser user);

    /**
     * Update an existing user.
     *
     * @param id         The unique identifier of the user to be updated.
     * @param updatedUser The updated user entity.
     * @return The updated AppUser, or null if the user is not found.
     */
    AppUser updateUser(Long id, AppUser updatedUser);

    /**
     * Delete a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     */
    void deleteUser(Long id);
}
