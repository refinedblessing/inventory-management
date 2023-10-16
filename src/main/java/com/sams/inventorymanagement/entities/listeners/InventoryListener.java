package com.sams.inventorymanagement.entities.listeners;

import com.sams.inventorymanagement.entities.Inventory;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;

public class InventoryListener {

    @PreRemove
    public void preRemove(Inventory inventory) {
        //  TODO add the items back to warehouse
        // Handle logic before removing the entity (e.g., log or other operations)
    }

    @PostLoad
    public void postLoad(Inventory inventory) {
        // Handle logic after the entity is loaded (if needed)
    }

    @PreUpdate
    public void preUpdate(Inventory inventory) {

    }

    @PostUpdate
    public void postUpdate(Inventory inventory) {
        // Handle logic after the entity is updated (if needed)
        if (inventory.getQuantity() == 0) {
            // Delete the inventory when the quantity reaches 0
            inventory.getStore().getInventories().remove(inventory);
            inventory.getItem().getInventories().remove(inventory);
        }
    }
}
