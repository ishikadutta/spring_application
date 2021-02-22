package com.example.spring_application.service.impl;

import com.example.spring_application.client.SearchClient;
import com.example.spring_application.dto.Products;
import com.example.spring_application.dto.RequestDTO;
import com.example.spring_application.dto.ResponseDTO;
import com.example.spring_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SearchClient searchClient;


    @Override
    public Products getProducts(RequestDTO request){
        Map<String,Object> products = searchClient.getProducts(request.getSearchTerm());
        List<Map<String, Object>> productResponseObjectList = (List<Map<String, Object>>) ((Map)products.get("response")).get("docs");
        Products  responseDTO = new Products();
        List<ResponseDTO> productDTOs = new ArrayList<>();
        for(int i=0;i<productResponseObjectList.size();i++){
            ResponseDTO responseDTO1 = new ResponseDTO();
            String title = (String)productResponseObjectList.get(i).get("name");
            responseDTO1.setTitle(title);
            String description = (String)productResponseObjectList.get(i).get("description");
            responseDTO1.setDescription(description);
            int b =(int)productResponseObjectList.get(i).get("isInStock");
            if(b==1){
                responseDTO1.setInStock(true);
            }
            else{
                responseDTO1.setInStock(false);
            }
            int salePrice =(int)productResponseObjectList.get(i).get("salePrice");
            responseDTO1.setSalesPrice(salePrice);

            productDTOs.add(responseDTO1);
        }

        responseDTO.setProducts(productDTOs);
        return responseDTO;

    }
}
