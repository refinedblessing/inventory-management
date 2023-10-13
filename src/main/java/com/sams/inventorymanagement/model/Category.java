package com.sams.inventorymanagement.model;

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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

//    Supplier can be moved to its own model
    /** The supplier name of the category. */
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;
    /** The supplier address of the category. */
    @Column(name = "supplier_address")
    private String supplierAddress;
    /** The supplier email of the category. */
    @Column(name = "supplier_email", nullable = false)
    private String supplierEmail;
    /** The supplier email of the category. */
    @Column(name = "supplier_phone", nullable = false)
    private String supplierPhone;
    /** The supplier contact person of the category. */
    @Column(name = "supplier_contact", nullable = false)
    private String supplierContact;
}
