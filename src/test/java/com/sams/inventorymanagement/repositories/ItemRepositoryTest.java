package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private Category sampleCategory;

    @BeforeEach
    public void setup() {
        // Create and save a sample supplier
        Supplier sampleSupplier = new Supplier();
        sampleSupplier.setName("Sample Supplier");
        sampleSupplier.setPhone("1234567890");

        // Save the supplier to the repository
        sampleSupplier = supplierRepository.save(sampleSupplier);

        // Create and save a sample category associated with the sample supplier
        sampleCategory = new Category();
        sampleCategory.setName("Sample Category");
        sampleCategory.setSupplier(sampleSupplier);

        // Save the category to the repository
        sampleCategory = categoryRepository.save(sampleCategory);
    }

    @Test
    public void saveValidItem() {
        Item item = new Item();
        item.setName("Valid Item");
        item.setShortDescription("Short description");
        item.setPrice(10.0);
        item.setQuantity(20);
        item.setCategory(sampleCategory);

        Item savedItem = itemRepository.save(item);

        assertNotNull(savedItem.getId());
        assertEquals("Valid Item", savedItem.getName());
        assertThat(savedItem.getCategory()).isEqualTo(sampleCategory);
    }

    @Test
    public void saveItemWithInvalidName() {
        Item item = new Item();
        item.setName(""); // Invalid name
        item.setShortDescription("Short description");
        item.setPrice(10.0);
        item.setQuantity(20);
        item.setCategory(sampleCategory);

        assertThrows(Exception.class, () -> itemRepository.saveAndFlush(item));
    }

    @Test
    void testFindItemByName_Success() {
        // Create and save a sample item associated with the sample category
        Item sampleItem = new Item();
        sampleItem.setName("Sample Item");
        sampleItem.setShortDescription("Short description");
        sampleItem.setPrice(10.0);
        sampleItem.setQuantity(20);
        sampleItem.setCategory(sampleCategory);
        sampleItem = itemRepository.save(sampleItem);

        // Search for the item by name
        Optional<Item> foundItem = itemRepository.findByName("Sample Item");

        assertThat(foundItem).isPresent().contains(sampleItem);
    }

    @Test
    void testFindItemByName_Failure() {
        // Search for a non-existent item by name
        Optional<Item> foundItem = itemRepository.findByName("NonExistent");

        assertThat(foundItem).isEmpty();
    }
}
