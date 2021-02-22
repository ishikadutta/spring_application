package com.example.spring_application.controller;

import com.example.spring_application.dto.Products;
import com.example.spring_application.dto.RequestDTO;
import com.example.spring_application.dto.ResponseDTO;
import com.example.spring_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping(path="/search")

public Products getProducts(@RequestBody RequestDTO request ){
        return productService.getProducts(request);
    }
}
