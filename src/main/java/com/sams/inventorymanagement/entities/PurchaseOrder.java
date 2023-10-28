package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a purchase order, including its associated store, purchase order items, and total quantity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "purchase_order")
public class PurchaseOrder {
    /**
     * The unique identifier for the purchase order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The store associated with this purchase order.
     */
    @ManyToOne
    @NotNull(message = "A store is required")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /**
     * The list of purchase order items included in this purchase order.
     */
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

    /**
     * The total quantity of items in this purchase order.
     */
    @Column(name = "total_quantity")
    private Integer totalQuantity = 0;

    @Column(name = "total_price")
    private Double totalPrice = 0.0;

    /**
     * The status of this purchase order.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;


    @Column(name = "last_updated")
    private LocalDate lastUpdated = LocalDate.now();
}

