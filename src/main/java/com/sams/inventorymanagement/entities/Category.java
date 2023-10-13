package com.sams.inventorymanagement.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    /** Unique id for the category. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    /** The name of the category. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** The items in a category. */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;
    /** The supplier of this category of items. */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
