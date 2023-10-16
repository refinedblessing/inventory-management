package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> searchItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity,
            @RequestParam(required = false) String categoryName
    ) {
        return itemService.searchItemsByCriteria(name, description, minPrice, maxPrice, minQuantity, maxQuantity, categoryName);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        if(item == null)
            throw new EntityNotFoundException("id: " + id);

        return item;
    }

//    @GetMapping("/with-supplier-info")
//    public List<ItemWithSupplierDTO> getItemsWithSupplierInfo() {
//        List<Item> items = itemService.getAllItems(); // You'll need to implement this method
//
//        return items.stream()
//                .map(item -> {
//                    ItemWithSupplierDTO dto = new ItemWithSupplierDTO();
//                    dto.setItemId(item.getId());
//                    dto.setItemName(item.getName());
//                    dto.setItemShortDescription(item.getShortDescription());
//                    dto.setItemPrice(item.getPrice());
//                    dto.setItemQuantity(item.getQuantity());
//
//                    Supplier supplier = item.getCategory().getSupplier();
//                    dto.setSupplierName(supplier.getName());
//                    dto.setSupplierAddress(supplier.getAddress());
//                    dto.setSupplierEmail(supplier.getEmail());
//                    dto.setSupplierPhone(supplier.getPhone());
//
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.createItem(item);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @Valid @RequestBody Item updatedItem) {
        return itemService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}

