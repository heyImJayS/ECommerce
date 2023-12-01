package dev.jays.ecommerce.services.ThirdPartyService;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImplementation")
public class SelfProductServiceFakeStoreImpl implements ProductServiceFakeStore {
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
