package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sams.inventorymanagement.dto.SignUpRequest;
import com.sams.inventorymanagement.enums.UserRole;
import com.sams.inventorymanagement.util.AuthorityMapper;
import com.sams.inventorymanagement.services.UserDetailsImpl;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @NotBlank(message = "Field can not be blank")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The email of the user.
     */
    @Email(message = "Email invalid")
    @NotBlank(message = "Field can not be blank")
    @NotNull(message = "Field can not be null")
    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    /**
     * The first name of the user.
     */
    @NotBlank(message = "Field can not be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The username of the user.
     */
    @NotBlank(message = "Field can not be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;


    /**
     * The last name of the user.
     */
    @NotBlank(message = "Field can not be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The roles of the user (admin, store_manager, store_staff).
     */
    @Column(name = "role", nullable = false)
    @NotNull(message = "Field can not be null")
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Store> stores = new HashSet<>();

    public boolean isAdmin() {
        return this.roles.contains(UserRole.ROLE_ADMIN);
    }

//    serving as DTO
    public AppUser(UserDetailsImpl user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = AuthorityMapper.mapAuthoritiesToUserRole(user.getAuthorities());
    }

    public AppUser(SignUpRequest user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
    }
}
