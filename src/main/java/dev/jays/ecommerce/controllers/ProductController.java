package dev.jays.ecommerce.controllers;


import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public void getALLProducts(){

    }
    //localhost:8080/products/{id}   <- URL  where 243 is ProductID
    //We mention variables inside curly braces
    @GetMapping("{id}")    //If we had not wrote ("/products") in annotation  -> @RequestMapping("/products") then we have to mention here like @GetMapping("/products/{id}")
    public GenericProductDTO getProductByID(@PathVariable("id") Long id){      //@PathVariable says that the id in "Long id" is nothing but same id in Path URL.
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    //As we know, while creating a product, client need to give product body in the request. So, @RequestBody annotation used.
    //@RequestBody says whatever is the request ..please convert that JSON to GenericProductDTO
    @PostMapping
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
