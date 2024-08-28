package com.cybersoft.uniclub06.service;

import com.cybersoft.uniclub06.dto.ProductDTO;
import com.cybersoft.uniclub06.request.AddProductRequest;

import java.util.List;

public interface ProductService {

    void addProduct(AddProductRequest request);

    List<ProductDTO> getProducts();
}
