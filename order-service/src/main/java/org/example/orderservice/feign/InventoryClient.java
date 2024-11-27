package org.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service") // Ensure this matches the name of your inventory service
public interface InventoryClient {
    @GetMapping("/inventory/check/{productId}/{quantity}")
    boolean checkInventory(@PathVariable String productId, @PathVariable int quantity);
}
