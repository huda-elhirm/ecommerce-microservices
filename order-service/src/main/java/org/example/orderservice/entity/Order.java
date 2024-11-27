package org.example.orderservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
//@Table(name = "order")
public class Order {
    @Id
    private String id;
    private String productId;
    private int quantity;

    // Getters and setters
}
