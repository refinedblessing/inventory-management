package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.entities.listeners.InventoryListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Inventory of item in a specific store.
 */
@Getter
@Setter
@Entity
@Table(name = "inventory")
@EntityListeners(InventoryListener.class)
public class Inventory {
    /** Unique id for the inventory.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Store associated with the inventory. */
    @ManyToOne
    @NotNull(message = "An inventory must have a store")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    /** Item associated with the inventory.*/
    @ManyToOne
    @NotNull(message = "An inventory must have an item")
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    /** The quantity in stock.*/
    @Min(value = 1, message = "Inventory must have at least 1 quantity")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The minimum quantity threshold: minimum quantity required in store.*/
    @Column(name = "threshold", nullable = false)
    private Integer threshold = 0;
}