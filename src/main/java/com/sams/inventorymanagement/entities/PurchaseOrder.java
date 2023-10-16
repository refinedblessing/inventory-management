package com.sams.inventorymanagement.entities;

import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @org.hibernate.validator.constraints.UUID(message = "Not a UUID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The store associated with this purchase order.
     */
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /**
     * The list of purchase order items included in this purchase order.
     */
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> purchaseOrderItems;

    /**
     * The total quantity of items in this purchase order.
     */
    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    /**
     * The status of this purchase order.
     */
    @Column(name = "status", nullable = false)
    @NotNull(message = "Field can not be null")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}

