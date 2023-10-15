package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.entities.listeners.InventoryListener;
import jakarta.persistence.*;
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
    @JoinColumn(name = "store_id")
    private Store store;
    /** Item associated with the inventory.*/
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    /** The quantity in stock.*/
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The minimum quantity threshold: minimum quantity required in store.*/
    @Column(name = "threshold")
    private Integer threshold = 0;
}