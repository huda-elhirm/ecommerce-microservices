package org.example.inventoryserver.controller;

import org.example.inventoryserver.entity.Inventory;
import org.example.inventoryserver.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable String productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable String productId, @RequestParam int quantity) {
        return inventoryService.updateInventory(productId, quantity);
    }

    // New method to add inventory
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }
    // New method to check inventory availability
    @GetMapping("/check/{productId}/{quantity}")
    public boolean checkInventory(@PathVariable String productId, @PathVariable int quantity) {
        Inventory inventory = inventoryService.getInventoryByProductId(productId);
        return inventory != null && inventory.getQuantity() >= quantity;
    }

}