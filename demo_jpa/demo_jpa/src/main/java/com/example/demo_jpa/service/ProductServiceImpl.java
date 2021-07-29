package com.example.demo_jpa.service;

import com.example.demo_jpa.entity.Category;
import com.example.demo_jpa.entity.Product;
import com.example.demo_jpa.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Product> ListAllProduct() {
        return productoRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("A");
        product.setCreateAt(new Date());
        return productoRepository.save(product);
    }

    @Override
    public List<Product> findProductoByCategory(Category category) {
        return productoRepository.findByCategory(category);
    }
}
