package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.AppUserDTO;
import com.sams.inventorymanagement.dto.SignUpRequest;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.services.AppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing application users (AppUser entities).
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "User Management Controller")
public class AppUserController extends BaseController {
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new AppUserController with the provided AppUserService.
     *
     * @param appUserService The service responsible for user-related operations.
     */
    public AppUserController(PasswordEncoder passwordEncoder, AppUserService appUserService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get a list of all users.
     *
     * @return A ResponseEntity containing a list of AppUser entities with an HTTP status of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        List<AppUserDTO> users = appUserService.getAllUsers().stream().map(AppUserDTO::fromAppUser).collect(Collectors.toList());
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
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        AppUser user = appUserService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(AppUserDTO.fromAppUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new user.
     *
     * @param signUpRequest The user signup info to be created.
     * @return A ResponseEntity containing the created AppUser with an HTTP status of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        AppUser user = new AppUser(signUpRequest);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        AppUser createdUser = appUserService.createUser(user);
        return new ResponseEntity<>(AppUserDTO.fromAppUser(createdUser), HttpStatus.CREATED);
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
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody AppUserDTO updatedUser) {
        AppUser user = appUserService.updateUser(getCurrentUser(), updatedUser);

        if (user != null) {
            return new ResponseEntity<>(AppUserDTO.fromAppUser(user), HttpStatus.OK);
        }  else {
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
//      an admin can delete themselves or non-admins
        AppUser user = appUserService.getUserById(id);
        if (user != null) {
            if (user.isAdmin()) {
                if (!(getCurrentUser().getId().equals(user.getId()))) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            appUserService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }  else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
