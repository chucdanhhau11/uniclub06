package com.cybersoft.uniclub06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductCategoryEntity> productCategories;
}
