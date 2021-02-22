package com.example.spring_application.service.impl;

import com.example.spring_application.dto.Products;
import com.example.spring_application.dto.RequestDTO;
import com.example.spring_application.dto.ResponseDTO;
import com.example.spring_application.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Products getProducts(RequestDTO request){
        Products  responseDTO = new Products();
        ResponseDTO product = new ResponseDTO();
        product.setDescription("One plus");
        product.setInStock(true);
        product.setSalesPrice(45000);
        responseDTO.setProducts(Arrays.asList(product));
        return responseDTO;

    }
}
