package dev.jays.ecommerce.controllers;

import dev.jays.ecommerce.dtos.GenericCategoryDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.dtos.GetProductTitleRequestDTO;
import dev.jays.ecommerce.dtos.ProductDto;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.services.CategoryServiceSelf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceSelf categoryServiceSelf;


    public CategoryController(@Qualifier("CategoryServiceImplementation") CategoryServiceSelf categoryServiceSelf){
        this.categoryServiceSelf = categoryServiceSelf;
    }

    //For a Given Category UUID get all the products belongs to that Category ...send all those product via List of ProductDto
    @GetMapping("/.{uuid}")
    public List<GenericProductDTO> getCategoryProducts(@PathVariable("uuid")String uuid){
        return categoryServiceSelf.getCategoryProducts(uuid);
    }
    @GetMapping("/titles/{uuid}")
    public List<String> getProductTitles(@PathVariable("uuid") String uuid){
        return categoryServiceSelf.getProductTitles(uuid);
    }

    //Implementation of the N+1 Problem. Were we are trying to get all the Titles of the Products In each Category UUIDs(given in the Request Body
    @GetMapping("/titles/")
    public List<String> getAllProductTitles(@RequestBody GetProductTitleRequestDTO requestDTO){
        List<String> uuids= requestDTO.getUuids();
        return categoryServiceSelf.getAllCategoryProductTitles(uuids);
    }
    @GetMapping
    List<GenericCategoryDTO> getAllCategories(){
        return categoryServiceSelf.getAllCategories();
    }

}
