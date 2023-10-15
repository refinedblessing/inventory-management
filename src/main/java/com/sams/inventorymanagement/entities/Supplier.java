package com.sams.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Supplier of items, supplied via categories.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "supplier")
public class Supplier {
    /** Unique id for the supplier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The supplier name. */
    @NotNull(message = "Field can not be null")
    @Column(name = "name", nullable = false)
    private String name;
    /** The supplier address. */
    @Column(name = "address")
    private String address;
    /** The supplier email. */
    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;
    /** The supplier phone. */
    @NotNull(message = "Field can not be null")
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10,14}$", message = "Invalid phone number format")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    /** The supplier's categories. */
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private List<Category> categories;
}
