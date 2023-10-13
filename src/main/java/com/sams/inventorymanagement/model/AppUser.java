package com.sams.inventorymanagement.model;

import com.sams.inventorymanagement.enums.UserRole;
import jakarta.persistence.*;

/**
 * User for authentication with our website.
 */
@Entity
@Table(name = "app_user")
public class AppUser {
    /** Unique id for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The encrypted password of the user. */
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
    /** The email of the user. */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    /** The first name of the user. */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /** The last name of the user. */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /** The user role(admin, store_manager, store_staff, user) */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * Sets the id.
     * @param id The id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the last name.
     * @param lastName The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the first name.
     * @param firstName The first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the email.
     * @param email The email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email.
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the password, this should be pre-encrypted.
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the encrypted password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username.
     * @return The username.
     */
    public String getUsername() {
        return firstName + " " + lastName;
    }

    /**
     * Sets the role.
     * @param role The role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets the role.
     *
     * @return The role.
     */
    public UserRole getRole() {
        return role;
    }
}
