package com.cybersoft.uniclub06.entity;

import com.cybersoft.uniclub06.entity.key.IdProductCategory;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "product_category")
public class ProductCategoryEntity {

    @EmbeddedId
    private IdProductCategory id;

    @ManyToOne
    @JoinColumn(name = "id_product", updatable = false, insertable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_category", updatable = false, insertable = false)
    private CategoryEntity category;
}
