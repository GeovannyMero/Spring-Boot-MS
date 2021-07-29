package com.example.demo_jpa.repository;

import com.example.demo_jpa.entity.Category;
import com.example.demo_jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategory(Category category);
}
