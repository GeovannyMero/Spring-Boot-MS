package com.example.demo_jpa.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name =  "tbl_categories")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}
