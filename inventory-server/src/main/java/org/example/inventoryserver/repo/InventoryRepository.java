package org.example.inventoryserver.repo;

import org.example.inventoryserver.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}