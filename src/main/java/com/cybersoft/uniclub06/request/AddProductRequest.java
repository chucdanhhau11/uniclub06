package com.cybersoft.uniclub06.request;

import org.springframework.web.multipart.MultipartFile;

public record AddProductRequest(
        String name,
        String desc,
        String information,
        double price,
        int idBrand,
        int idColor,
        int idSize,
        MultipartFile files,
        int quantity,
        double priceSize
) {}
