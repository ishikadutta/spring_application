package com.example.spring_application.service.impl;

import com.example.spring_application.client.SearchClient;
import com.example.spring_application.dto.Products;
import com.example.spring_application.dto.RequestDTO;
import com.example.spring_application.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts(){

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object > searchTermMockObject = null;
        try {
            searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object > locationMockObject = null;
        try {
            locationMockObject = objectMapper.readValue(new URL("file:src/test/resources/location.mock"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenReturn(locationMockObject);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocation("jakarta");
        productService.getProducts(requestDTO);
        Products response = productService.getProducts(requestDTO);

        assertEquals(response.getProducts().size(),10);
        assertEquals(response.getLocationBasedProducts(),10);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");
    }
    @Test
    void testGetProductsExceptionTest() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object > searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"),Map.class);
     //   Map<String, Object > locationMockObject = null;
//        try {
//            locationMockObject = objectMapper.readValue(new URL("file:src/test/resources/location.mock"), Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenThrow(NullPointerException.class);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocation("jakarta");
        Products response = productService.getProducts(requestDTO);

        assertEquals(response.getProducts().size(),10);
        assertEquals(response.getLocationBasedProducts(),null);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");
    }

}