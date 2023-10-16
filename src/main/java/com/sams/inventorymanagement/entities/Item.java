package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * An item available for store stocking.
 */
@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {
    /** Unique id for the item. */
    @Id
    @NotNull(message = "Field can not be null")
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
    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Inventory> inventories;
    /** The purchaseOrders that have this item. */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> purchaseOrders;
}