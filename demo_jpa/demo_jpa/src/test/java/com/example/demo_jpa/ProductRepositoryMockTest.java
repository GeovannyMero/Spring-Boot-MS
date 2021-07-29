package com.example.demo_jpa;

import com.example.demo_jpa.entity.Category;
import com.example.demo_jpa.entity.Product;
import com.example.demo_jpa.repository.ProductoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void whenFindCategory_(){
        Product producto01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("A")
                .createAt(new Date()).build();

        productoRepository.save(producto01);

        List<Product> founds = productoRepository.findByCategory(producto01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }
}
