package com.sams.inventorymanagement.model;

import jakarta.persistence.*;

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
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** The short description of the item. */
    @Column(name = "short_description", nullable = false)
    private String shortDescription;
    /** The long description of the item. */
    @Column(name = "long_description")
    private String longDescription;
    /** The price of the item. */
    @Column(name = "price", nullable = false)
    private Double price;
    /** The quantity of the item. */
    @Column(name = "quantity", nullable = false)
    private int quantity;
    /** The category of the item. */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The quantity.
     */
    public void setQuantity(int quantity) {
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
}