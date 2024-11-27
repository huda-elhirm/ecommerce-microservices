package org.example.orderservice.service;

import org.example.orderservice.dto.Product;
import org.example.orderservice.entity.Order;
import org.example.orderservice.feign.InventoryClient;
import org.example.orderservice.feign.ProductClient;
import org.example.orderservice.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private InventoryClient inventoryClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        // Check if the product exists
        Product product = productClient.getProductById(order.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // Check if there is sufficient inventory for the order
        if (!checkInventory(order.getProductId(), order.getQuantity())) {
            throw new RuntimeException("Insufficient inventory for product ID: " + order.getProductId());
        }

        // If product exists and inventory is sufficient, save the order
        return orderRepository.save(order);
    }

    // Updated method to check inventory
    private boolean checkInventory(String productId, int quantity) {
        // Call the inventory service to check the available quantity
        return inventoryClient.checkInventory(productId, quantity);
    }
}