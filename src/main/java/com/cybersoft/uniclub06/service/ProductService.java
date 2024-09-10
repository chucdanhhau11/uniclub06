package com.cybersoft.uniclub06.service;

import com.cybersoft.uniclub06.dto.ProductDTO;
import com.cybersoft.uniclub06.request.AddProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(AddProductRequest request);

    List<ProductDTO> getProducts(int page);

    ProductDTO getDetailProduct(int id);
}
