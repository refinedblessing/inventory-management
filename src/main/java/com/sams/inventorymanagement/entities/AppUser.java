package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sams.inventorymanagement.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an application user, including their unique identifier, password,
 * email, first name, last name, and user role.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The encrypted password of the user.
     */
    @JsonIgnore
    @NotNull(message = "Field can not be null")
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    /**
     * The email of the user.
     */
    @Email(message = "Email invalid")
    @NotNull(message = "Field can not be null")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The first name of the user.
     */
    @NotNull(message = "Field can not be null")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotNull(message = "Field can not be null")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The role of the user (admin, store_manager, store_staff).
     */
    @Column(name = "role", nullable = false)
    @NotNull(message = "Field can not be null")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
