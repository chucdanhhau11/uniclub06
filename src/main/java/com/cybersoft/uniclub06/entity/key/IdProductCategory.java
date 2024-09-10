package com.cybersoft.uniclub06.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdProductCategory implements Serializable {
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_category")
    private int idCategory;
}
