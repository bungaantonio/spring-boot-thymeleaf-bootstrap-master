package com.example.demo.service;

import com.example.demo.dto.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);

        return products;
    }

    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);

    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

}
