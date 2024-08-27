package com.cybersoft.uniclub06.controller;

import com.cybersoft.uniclub06.service.FileService;
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

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestParam MultipartFile file){
        fileService.saveFile(file);

        return new ResponseEntity<>("Hello add product", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getProduct(){


        return new ResponseEntity<>("Hello get product", HttpStatus.OK);
    }
}
