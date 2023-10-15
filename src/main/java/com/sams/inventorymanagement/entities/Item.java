package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * An item available for store stocking.
 */
@Entity
@Table(name = "item")
public class Item {
    /** Unique id for the item. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The name of the item. */
    @NotNull(message = "Field can not be null")
    @Size(min = 2, max = 255, message = "Name should have a minimum of 2 Characters & max 255")
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** The short description of the item. */
    @NotNull(message = "Field can not be null")
    @Size(min = 10, max = 255, message = "Short Description should have a minimum of 10 Characters & max 255")
    @Column(name = "short_description", nullable = false)
    private String shortDescription;
    /** The long description of the item. */
    @Size(min = 100, message = "Long Description should have a minimum of 100 Characters")
    @Column(name = "long_description")
    private String longDescription;
    /** The price of the item. */
    @NotNull(message = "Field can not be null")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @Column(name = "price", nullable = false)
    private Double price;
    /** The quantity of the item. */
    @NotNull(message = "Field can not be null")
    @Min(value = 1, message = "You must have at least 1 item")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The category of the item. */
    @NotNull(message = "Field can not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    /** The inventories that have this item. */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    /**
     * Gets the price of the item.
     * @return The price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     * @param price The price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the item.
     * @return The quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The quantity.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the long description of the item.
     * @return The long description.
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the long description of the item.
     * @param longDescription The long description.
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Gets the short description of the item.
     * @return The short description.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short description of the item.
     * @param shortDescription The short description.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the name of the item.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the id of the item.
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the item.
     * @param id The id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the category of the item.
     * @return The category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the item.
     * @param category The category.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets the inventories for this item.
     * @return The inventories.
     */
    public List<Inventory> getInventories() {
        return inventories;
    }
}