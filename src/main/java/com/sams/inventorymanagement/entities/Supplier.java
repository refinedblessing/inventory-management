package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
    @NotNull(message = "Field can not be null")
    @Column(name = "name", nullable = false)
    private String name;
    /** The supplier address. */
    @Column(name = "address")
    private String address;
    /** The supplier email. */
    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;
    /** The supplier phone. */
    @NotNull(message = "Field can not be null")
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10,14}$", message = "Invalid phone number format")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    /** The supplier's categories. */
    @OneToMany(mappedBy = "supplier")
    private List<Category> categories;

    /**
     * Gets the unique identifier of the supplier.
     *
     * @return The unique identifier (ID) of the supplier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the supplier.
     *
     * @param id The unique identifier (ID) to set for the supplier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the supplier.
     *
     * @return The name of the supplier.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param name The name to set for the supplier.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the supplier.
     *
     * @return The address of the supplier.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the supplier.
     *
     * @param address The address to set for the supplier.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the email of the supplier.
     *
     * @return The email of the supplier.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the supplier.
     *
     * @param email The email to set for the supplier.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the supplier.
     *
     * @return The phone number of the supplier.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the supplier.
     *
     * @param phone The phone number to set for the supplier.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the list of categories associated with the supplier.
     *
     * @return The list of categories associated with the supplier.
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories associated with the supplier.
     *
     * @param categories The list of categories to set for the supplier.
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
