package dev.jays.ecommerce.controllers;


import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.response.ApiEntity;
import dev.jays.ecommerce.response.ApiResponseObject;
import dev.jays.ecommerce.security.JwtObject;
import dev.jays.ecommerce.security.TokenValidator;
import dev.jays.ecommerce.services.ProductServiceSelf;
import dev.jays.ecommerce.services.ThirdPartyService.ProductServiceFakeStore;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This productController going to implement REST APIs. So we add the Annotation @RestController
@RestController
@RequestMapping("/products/")
public class ProductController  {
     //Field Injection
    //--------------------------------------------------------------
    //@Autowired  <- This Annotation will autometically inject over variable/field which is not recommended
    @Qualifier("ProductServiceSelfImplementation")
    private ProductServiceSelf productServiceSelf;

    @Autowired
    private TokenValidator tokenValidator;

    //Constructor Injection
    //---------------------------------------------------------
    //Here we are injecting the dependency via constructor
    //@Qualifier Annotation specifies which class object. Because there are two implementations of ProductService Interface
    //This qualifier throwing error while MVC API testing.. so we need to remove it @Qualifier annotation
    // Ref (4th point read):- https://www.w3docs.com/snippets/java/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-api-using-mongodb.html
    public ProductController( /* @Qualifier("ProductServiceSelfImplementation") */ ProductServiceSelf productServiceSelf){
        this.productServiceSelf = productServiceSelf;
    }
/*
    //Implementation of @Primary by without specifying @Qualifier
    public ProductController(ProductService productService){
        this.productService=productService;
    }
*/
    //Setter Injection (Not Recommended)
    //-------------------------------------------------
/*
    @Autowired
    public void setProductService(ProductService productService){
        this.productService= productService;
    }
*/

    @GetMapping   //  /products
    public ResponseEntity<ApiResponseObject> getALLProducts(){
        List<GenericProductDTO> allProducts = new ArrayList<>();
        String message;
        try {
            allProducts = productServiceSelf.getAllProducts();
            if (allProducts.isEmpty()) {
                message= "No products available";
                return new ResponseEntity<ApiResponseObject>(new ApiEntity<List<GenericProductDTO>>(message), HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<ApiResponseObject>(new ApiEntity<List<GenericProductDTO>>(allProducts), HttpStatus.OK);
            }
        }catch(Exception e){
            message= e.getMessage();
            return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(message), HttpStatus.OK);
        }


    }
    //localhost:8080/products/{id}   <- URL  where 243 is ProductID
    //We mention variables inside curly braces
    @GetMapping("{id}")    //If we had not written ("/products") in annotation  -> @RequestMapping("/products") then we have to mention here like @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponseObject> getProductByID(@PathVariable("id")UUID uuid) throws NotFoundException{      //@PathVariable says that the id in "Long id" is nothing but same id in Path URL.

        String message = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus status= HttpStatus.OK;
        try{
            message = "Success access";
            GenericProductDTO product= productServiceSelf.getProductById(uuid);
            return new ResponseEntity<ApiResponseObject>(new ApiEntity<GenericProductDTO>(message, product),httpHeaders,status);
        }
        catch(Exception e){
            return new ResponseEntity<ApiResponseObject>(new ApiEntity<GenericProductDTO>(e.getMessage()),httpHeaders, status);
        }

    }

    @DeleteMapping("{id}")           //   /products/{id}
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        //return productService.deleteProduct(id);
        //returning customized response after setting Http Response code
        ResponseEntity<GenericProductDTO> response = new ResponseEntity<>(
                productServiceSelf.deleteProduct(id), HttpStatus.NOT_FOUND);
        return response;
    }
    //As we know, while creating a product, client need to give product body in the request. So, @RequestBody annotation used.
    //@RequestBody says whatever is the request ...please convert that JSON to GenericProductDTO
    @PostMapping                     //  /products/
    public GenericProductDTO createProduct(@RequestBody Product product){   //We took GenericProductDTO because the Output of the API have same attributes as GenericProductDTO
        /*
        fetch('https://fakestoreapi.com/products',{
                method:"POST",
                body:JSON.stringify(
                {
                        title: 'test product',
                        price: 13.5,
                        description: 'lorem ipsum set',
                        image: 'https://i.pravatar.cc',
                        category: 'electronic'
                }
            )
        })
            .then(res=>res.json())
            .then(json=>console.log(json))

        OUTPUT:-
        {
                id:31,
                title:'...',
                price:'...',
                category:'...',
                description:'...',
                image:'...'
        }
        */
        return productServiceSelf.createProduct(product);

    }
    @PutMapping("{id}")
    public void updateProductById(){
        
    }

}
