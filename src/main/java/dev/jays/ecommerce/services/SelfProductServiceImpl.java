package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImplementation")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDTO getProductById(Long id) {

        return null;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return null;
    }
}
