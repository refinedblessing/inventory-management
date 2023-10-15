package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

