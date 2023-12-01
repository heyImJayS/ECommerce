package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductServiceSelf {

    public GenericProductDTO getProductById(UUID productUUID) throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO product);
    public List<GenericProductDTO> getAllProducts();
    public GenericProductDTO deleteProduct(Long id);
}
