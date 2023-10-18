package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing application users (AppUser entities).
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Find a user by their email.
     *
     * @param email The email of the user.
     * @return An Optional containing the found AppUser, or an empty Optional if not found.
     */
    Optional<AppUser> findByEmail(String email);

    /**
     * Check if a user with the specified email exists.
     *
     * @param email The email to check for existence.
     * @return true if a user with the specified email exists, false otherwise.
     */
    Boolean existsByEmail(String email);
}
