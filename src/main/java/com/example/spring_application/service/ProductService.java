package com.example.spring_application.service;

import com.example.spring_application.dto.Products;
import com.example.spring_application.dto.RequestDTO;

public interface ProductService {
   Products getProducts(RequestDTO requestDTO);
 //  Products getLocation(RequestDTO requestDTO);
}
