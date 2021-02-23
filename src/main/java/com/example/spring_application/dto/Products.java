package com.example.spring_application.dto;

import java.util.List;

public class Products {

      private List<ResponseDTO> products ;
private List<ResponseDTO> locationBasedProducts;

    public List<ResponseDTO> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<ResponseDTO> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }

    public List<ResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ResponseDTO> products) {
        this.products = products;
    }
}
