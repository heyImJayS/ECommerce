package dev.jays.ecommerce.controllers;


import org.springframework.web.bind.annotation.*;

// This productController going to implement REST APIs. So we add the Annotation @RestController
@RestController
@RequestMapping("/api/v1/products/")
public class ProductController  {
    @GetMapping
    public void getALLProducts(){

    }
    //localhost:8080/products/{id}   <- URL  where 243 is ProductID
    //We mention variables inside curly braces
    @GetMapping("{id}")    //If we had not wrote ("/products") in annotation  -> @RequestMapping("/products") then we have to mention here like @GetMapping("/products/{id}")
    public String getProductByID(@PathVariable("id") Long id){      //@PathVariable says that the id in "Long id" is nothing but same id in Path URL.
        return "Here is the product" + id;
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping
    public void createProduct(){

    }
    @PutMapping("{id}")
    public void updateProductById(){

    }

}
