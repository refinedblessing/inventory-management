package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.enums.StoreType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a store where items are available for stocking and sale.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {
    /**
     * Unique identifier for the store.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the store. It cannot be null and must be unique.
     */
    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "Store name cannot be null")
    @NotBlank(message = "Store name cannot be blank")
    private String name;

    /**
     * The address of the store. It cannot be null.
     */
    @Column(name = "address", nullable = false)
    @NotNull(message = "Address cannot be null")
    private String address;

    /**
     * The email of the store.
     */
    @Column(name = "email")
    private String email;

    /**
     * The phone number of the store. It cannot be null.
     */
    @Column(name = "phone", nullable = false)
    @NotNull(message = "Phone number cannot be null")
    private String phone;

    /**
     * The type of the store. It cannot be null.
     */
    @Column(name = "type", nullable = false)
    @NotNull(message = "Type cannot be null")
    @Enumerated(EnumType.STRING)
    private StoreType type;

    /**
     * The list of inventories associated with this store.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories;

    /**
     * The opening date of the store. It cannot be null.
     */
    @Column(name = "opening_date", nullable = false)
    @NotNull(message = "Opening date cannot be null")
    private LocalDate openingDate;
}
