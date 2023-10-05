package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    //Typically methods in the controllers are present in the Services. Because Controllers internally calls Service methods

    public GenericProductDTO getProductById(Long id) throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO product);
    public List<GenericProductDTO> getAllProducts();
    public GenericProductDTO deleteProduct(Long id);
}
