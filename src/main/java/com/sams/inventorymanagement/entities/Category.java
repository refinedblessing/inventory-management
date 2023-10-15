package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {
    /** Unique id for the category. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The name of the category. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** The items in a category. */
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;
    /** The supplier of this category of items. */
    @ManyToOne
    @JsonIgnore
    @NotNull(message = "Include a Supplier")
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}
