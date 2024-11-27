package org.example.orderservice.feign;


import org.example.orderservice.dto.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable String id);

   /* @GetMapping("/products")
    List<Product> getProducts();*/

}
