package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a category for grouping items. This entity contains a unique identifier, name, associated items,
 * and a reference to the supplier of the category.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {
    /**
     * The unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the category. It is required and must be unique among all categories.
     */
    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "Category name is required")
    @Size(min = 1, max = 255, message = "Category name should have a minimum of 1 character and a maximum of 255 characters")
    private String name;

    /**
     * The list of items associated with this category.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Item> items;

    /**
     * The supplier of this category of items. It is required and cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
