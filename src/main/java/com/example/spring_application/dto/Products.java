package com.example.spring_application.dto;

import java.util.ArrayList;
import java.util.List;

public class Products {

      private List<ResponseDTO> products ;

    public List<ResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ResponseDTO> products) {
        this.products = products;
    }
}
