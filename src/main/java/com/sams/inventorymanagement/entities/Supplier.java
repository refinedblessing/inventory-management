package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Supplier of items, supplying via categories.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "supplier")
public class Supplier {
    /**
     * Unique identifier for the supplier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the supplier. It cannot be null and must be unique.
     */
    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    /**
     * The address of the supplier.
     */
    @Column(name = "address")
    private String address;

    /**
     * The email of the supplier. Should be a valid email address.
     */
    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;

    /**
     * The phone number of the supplier. It cannot be null and must match the specified pattern.
     */
    @Column(name = "phone", nullable = false)
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10,14}$", message = "Invalid phone number format")
    private String phone;

    /**
     * The list of categories associated with this supplier.
     */
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private List<Category> categories;
}
