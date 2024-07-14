package dev.jays.ecommerce.controllers;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.services.ProductServiceSelf;
import dev.jays.ecommerce.dtos.GenericCategoryDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceSelf productServiceSelf;

    //This Library Jackson used to convert Java Object to JSON
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(productServiceSelf.getAllProducts())
                .thenReturn(new ArrayList<>());

        //MockMVC will perform a GET request on path "/"
        //Whatever we expect from the GET request, we provide those thing in 'andExpect' and Test.
        mockMvc.perform(get("/products/"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }

    @Test
    void returnListOfProductsWhenProductExists() throws Exception {
        List<GenericProductDTO> products = new ArrayList<>();
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());

        when(productServiceSelf.getAllProducts())
                .thenReturn(products);
        mockMvc.perform(get("/products/"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(products))
                );
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDTO productToCreate = new GenericProductDTO();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        GenericProductDTO expectedProduct = new GenericProductDTO();
        expectedProduct.setId(1001L);
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("mobile phones");
        expectedProduct.setDescription("Best iPhone Ever");

        when(
                productServiceSelf.createProduct(any()))
                .thenReturn(
                expectedProduct
        );

        mockMvc.perform(
                post("/products/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToCreate))
                ).andExpect(
                        content().string(objectMapper.writeValueAsString(expectedProduct))
                ).andExpect(status().is(200))
                .andExpect(jsonPath("$.student.name", is("Naman")))
                .andExpect(jsonPath("$.length()", is(2)));

    }
*/
}

/*
        {
            student: {
                name:"Naman",
                email:"naman.bhalla@scaler.com ,
                age:32
             }
        }

        In the above json:-
        jsonPath= $.student.name

        */
