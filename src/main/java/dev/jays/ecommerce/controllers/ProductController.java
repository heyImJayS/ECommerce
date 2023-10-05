package dev.jays.ecommerce.controllers;


import dev.jays.ecommerce.dtos.ExceptionDataDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This productController going to implement REST APIs. So we add the Annotation @RestController
@RestController
@RequestMapping("/products/")
public class ProductController  {
     //Field Injection
    //--------------------------------------------------------------
    //@Autowired  <- This Annotation will autometically inject over variable/field which is not recommended
    private ProductService productService;


    //Constructor Injection
    //---------------------------------------------------------
    //Here we are injecting the dependency via constructor
    //@Qualifier Annotation specifies which class object. Because there are two implementations of ProductService Interface
    public ProductController(@Qualifier("FakeStoreProductServiceImplementation") ProductService productService){
        this.productService=productService;
    }


    //Setter Injection (Not Recommended)
    //-------------------------------------------------
    /*
    @Autowired
    public void setProductService(ProductService productService){
        this.productService= productService;
    }
    */

    @GetMapping   //  /products
    public List<GenericProductDTO> getALLProducts(){
        return productService.getAllProducts();

    }
    //localhost:8080/products/{id}   <- URL  where 243 is ProductID
    //We mention variables inside curly braces
    @GetMapping("{id}")    //If we had not wrote ("/products") in annotation  -> @RequestMapping("/products") then we have to mention here like @GetMapping("/products/{id}")
    public GenericProductDTO getProductByID(@PathVariable("id") Long id) throws NotFoundException{      //@PathVariable says that the id in "Long id" is nothing but same id in Path URL.

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")           //   /products/{id}
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        //return productService.deleteProduct(id);
        //returning customized response after setting Http Response code
        ResponseEntity<GenericProductDTO> response = new ResponseEntity<>(
                productService.deleteProduct(id), HttpStatus.NOT_FOUND);
        return response;
    }
    //As we know, while creating a product, client need to give product body in the request. So, @RequestBody annotation used.
    //@RequestBody says whatever is the request ..please convert that JSON to GenericProductDTO
    @PostMapping                     //  /products/
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){   //We took GenericProductDTO because the Output of the API have same attributes as GenericProductDTO
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
        return productService.createProduct(product);

    }
    @PutMapping("{id}")
    public void updateProductById(){
        
    }

}
