package org.example.inventoryserver.service;

import org.example.inventoryserver.entity.Inventory;
import org.example.inventoryserver.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryByProductId(String productId) {
        return inventoryRepository.findById(productId).orElse(null);
    }

    public Inventory updateInventory(String productId, int quantity) {
        return inventoryRepository.findById(productId)
                .map(inventory -> {
                    inventory.setQuantity(quantity);
                    return inventoryRepository.save(inventory);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    // New method to add inventory
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}