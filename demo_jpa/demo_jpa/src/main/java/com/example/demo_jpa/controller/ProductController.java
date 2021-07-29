package com.example.demo_jpa.controller;

import com.example.demo_jpa.entity.Category;
import com.example.demo_jpa.entity.Product;
import com.example.demo_jpa.service.ProductService;
import com.example.demo_jpa.service.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> ListProducts(@RequestParam(name = "categoryId", required = false) int categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            if (0 == categoryId) {
                products = productService.ListAllProduct();
                if (products.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
            } else {
                products = productService.findProductoByCategory(Category.builder().id(categoryId).build());
                if (products.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> GetProduct(@PathVariable("id") long id) {
        Product product = new Product();
        try {
            product = productService.getProduct(id);
            if (null == product) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.FormatMessage(result));
        }
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    //update ==> @PutMapping(value = "/id")
    //delete ==> @DeleteMapping(value = "/{id}")

    private String FormatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);

        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;

    }
}
