package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;

import java.util.List;

/**
 * Supplier of items, supplied via categories.
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    /** Unique id for the supplier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The supplier name. */
    @Column(name = "name", nullable = false)
    private String name;
    /** The supplier address. */
    @Column(name = "address")
    private String address;
    /** The supplier email. */
    @Column(name = "email", nullable = false)
    private String email;
    /** The supplier phone. */
    @Column(name = "phone", nullable = false)
    private String phone;
    /** The supplier's categories. */
    @OneToMany(mappedBy = "supplier")
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
