package com.example.demo.request;

import java.util.Date;

import com.example.demo.dto.Product;

public record CreateProductInput(String name, String description, Date dueDate) {
    public Product toProduct() {
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setDueDate(dueDate);

        return product;
    }
}
