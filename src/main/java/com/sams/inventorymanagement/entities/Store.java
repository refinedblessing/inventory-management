package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.enums.StoreType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * A store.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {
    /** Unique id for the store. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The name of the store. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** The store address. */
    @Column(name = "address", nullable = false)
    private String address;
    /** The store email. */
    @Column(name = "email")
    private String email;
    /** The store phone of the category. */
    @Column(name = "phone", nullable = false)
    private String phone;
    /** The store type. */
    @Column(name = "type", nullable = false)
    @NotNull(message = "Field can not be null")
    @Enumerated(EnumType.STRING)
    private StoreType type;
    /** The inventories of the store. */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories;
    /** The opening date of the store. */
    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;
}
