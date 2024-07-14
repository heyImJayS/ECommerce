package dev.jays.ecommerce.controllers;

import dev.jays.ecommerce.dtos.request.OrderCreateRequestDTO;
import dev.jays.ecommerce.models.Order;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.repositories.ProductRepository;
import dev.jays.ecommerce.response.ApiEntity;
import dev.jays.ecommerce.response.ApiResponseObject;
import dev.jays.ecommerce.services.CategoryServiceSelf;
import dev.jays.ecommerce.services.OrderService;
import dev.jays.ecommerce.services.ProductServiceSelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryServiceSelf categoryServiceSelf;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/place")
    public ResponseEntity<ApiResponseObject> createOrder(@RequestBody OrderCreateRequestDTO orderCreateRequestDTO){
        String result="";
        Order newOrder = new Order();
        List<Product> products= new ArrayList<>();
        try {
            if (orderCreateRequestDTO != null && !orderCreateRequestDTO.getProducts().isEmpty()) {
                for (String productId : orderCreateRequestDTO.getProducts()) {
                    Optional<Product> response = productRepository.findById(UUID.fromString(productId));
                    Product product= null;
                    if(response!= null){
                        product= response.get();
                        products.add(product);
                    }
                }
            }
            if(!products.isEmpty()){
                newOrder.setProducts(products);
                result=  orderService.createOrder(newOrder);
                result= "Order Id: "+result+" placed successfully !!!";
                return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(result), HttpStatus.OK);
            }
            result="No product found to place order";
            return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(result), HttpStatus.NOT_FOUND);

        }
        catch(Exception e){
            return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
