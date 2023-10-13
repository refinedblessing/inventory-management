package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.entities.listeners.InventoryListener;
import jakarta.persistence.*;

/**
 * Inventory of item in a specific store.
 */
@Entity
@Table(name = "inventory")
@EntityListeners(InventoryListener.class)
public class Inventory {

    /** Unique id for the inventory. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Store associated with the inventory. */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    /** Item associated with the inventory. */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    /** The quantity in stock. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The minimum quantity threshold: minimum quantity required in store. */
    @Column(name = "threshold")
    private Integer threshold = 0;

    /**
     * Gets the minimum quantity threshold.
     * @return The threshold.
     */
    public Integer getThreshold() {
        return threshold;
    }

    /**
     * Sets the minimum quantity threshold.
     * @param threshold The threshold.
     */
    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    /**
     * Gets the quantity in stock.
     * @return The quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity in stock of the item.
     * @param quantity The quantity to be set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the item.
     * @return The item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item.
     * @param item The item to be set.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the ID of the inventory.
     * @return The ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the inventory.
     * @param id The ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}