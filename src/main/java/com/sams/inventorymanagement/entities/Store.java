package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;

import java.util.List;

/**
 * A store.
 */
@Entity
@Table(name = "store")
public class Store {
    /** Unique id for the store. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The name of the store. */
    @Column(name = "name", nullable = false)
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
    @Column(name = "phone", nullable = false)
    /** The inventories of the store. */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
