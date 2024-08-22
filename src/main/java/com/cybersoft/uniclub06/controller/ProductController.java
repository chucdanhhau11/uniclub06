package com.cybersoft.uniclub06.controller;

import com.cybersoft.uniclub06.request.AddProductRequest;
import com.cybersoft.uniclub06.response.BaseResponse;
import com.cybersoft.uniclub06.service.FileService;
import com.cybersoft.uniclub06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFile(@RequestParam MultipartFile file){
        fileService.saveFile(file);

        return new ResponseEntity<>("Hello add product", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(AddProductRequest request){
        productService.addProduct(request);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Succes !");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
