package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an item included in a purchase order. This entity associates an item with a specific purchase order
 * and indicates the quantity of that item to be included in the order.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "purchase_order_item")
public class PurchaseOrderItem {
    /**
     * Unique identifier for the purchase order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The purchase order to which this item belongs. It cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    /**
     * The item being ordered. It cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    /**
     * The quantity of the item to be included in the purchase order. It cannot be null and should be at least 1.
     */
    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity should be at least 1")
    private Integer quantity;
}
