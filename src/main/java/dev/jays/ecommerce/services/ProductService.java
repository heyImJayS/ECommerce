package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Product;

public interface ProductService {
    //Typically methods in the controllers are present in the Services. Because Controllers internally calls Service methods

    public GenericProductDTO getProductById(Long id);
    public GenericProductDTO createProduct(GenericProductDTO product);
}
