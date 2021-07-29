package com.example.demo_jpa.service;

import com.example.demo_jpa.entity.Category;
import com.example.demo_jpa.entity.Product;
import java.util.List;

public interface ProductService {
    public List<Product> ListAllProduct();
    public Product getProduct(Long id);
    public  Product createProduct(Product product);
    public List<Product> findProductoByCategory(Category category);
}
