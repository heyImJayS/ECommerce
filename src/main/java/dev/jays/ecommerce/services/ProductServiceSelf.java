package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductServiceSelf {

    public GenericProductDTO getProductById(UUID productUUID) throws NotFoundException;
    public GenericProductDTO createProduct(Product product);
    public List<GenericProductDTO> getAllProducts();
    public GenericProductDTO deleteProduct(Long id);
}
