package com.example.spring_application.service.impl;

import com.example.spring_application.client.SearchClient;
import com.example.spring_application.constants.SolrFieldNames;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService, SolrFieldNames {
    @Autowired
    private SearchClient searchClient;


    @Override
    public Products getProducts(RequestDTO request){

        //        Products  responseDTO = new Products();
//        List<ResponseDTO> productDTOs = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
      //  String locationQuery = SolrFieldNames.STOCK_LOCATION  + ":\"" + request.getLocation() + "\"";
       // List<ResponseDTO> productDTOS = getSearchTermBaseProducts(request.getSearchTerm());
        Products responseDTO = new Products();
        Runnable runnableTask1=()-> {
            Map<String, Object> products = searchClient.getProducts(request.getSearchTerm());
            List<Map<String, Object>> productResponseObjectList = (List<Map<String, Object>>) ((Map) products.get("response")).get("docs");

            List<ResponseDTO> productDTOs = new ArrayList<>();
            for (int i = 0; i < productResponseObjectList.size(); i++) {
                ResponseDTO responseDTO1 = new ResponseDTO();
                String title = (String) productResponseObjectList.get(i).get(NAME);
                responseDTO1.setTitle(title);
                String description = (String) productResponseObjectList.get(i).get(DESCRIPTION);
                responseDTO1.setDescription(description);
                int b = (int) productResponseObjectList.get(i).get(IN_STOCK);
                if (b == 1) {
                    responseDTO1.setInStock(true);
                } else {
                    responseDTO1.setInStock(false);
                }
                double salePrice = (double) productResponseObjectList.get(i).get("salePrice");
                responseDTO1.setSalesPrice((int) salePrice);

                productDTOs.add(responseDTO1);
            }
            System.out.println("Task 1:" + Thread.currentThread().getName());
            responseDTO.setProducts(productDTOs);
        };

Runnable runnableTask2=()-> {

    Map<String, Object> products2 = searchClient.getProducts("stockLocation:" + request.getLocation());
    List<Map<String, Object>> productResponseObjectList2 = (List<Map<String, Object>>) ((Map) products2.get("response")).get("docs");
    List<ResponseDTO> productDTOs2 = new ArrayList<>();
    for (int i = 0; i < productResponseObjectList2.size(); i++) {
        ResponseDTO responseDTO1 = new ResponseDTO();
        // String stockLocation = (String)productResponseObjectList2.get(i).get("stockLocation");

        String title = (String) productResponseObjectList2.get(i).get(NAME);
        responseDTO1.setTitle(title);
        String description = (String) productResponseObjectList2.get(i).get(DESCRIPTION);
        responseDTO1.setDescription(description);
        int b = (int) productResponseObjectList2.get(i).get(IN_STOCK);
        if (b == 1) {
            responseDTO1.setInStock(true);
        } else {
            responseDTO1.setInStock(false);
        }
        double salePrice = (double) productResponseObjectList2.get(i).get("salePrice");
        responseDTO1.setSalesPrice((int) salePrice);

        productDTOs2.add(responseDTO1);
    }
    System.out.println("Task 2:" + Thread.currentThread().getName());
    responseDTO.setLocationBasedProducts(productDTOs2);
};
       // responseDTO.setLocationBasedProducts(productDTOs2);
        executor.execute(runnableTask1);
        executor.execute(runnableTask2);
        executor.shutdown();
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        return responseDTO;

    }

}
