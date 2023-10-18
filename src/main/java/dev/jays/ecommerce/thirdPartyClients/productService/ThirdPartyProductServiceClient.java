package dev.jays.ecommerce.thirdPartyClients.productService;

import dev.jays.ecommerce.thirdPartyClients.productService.fakestore.FakeStoreProductDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;

import java.util.List;


//This nothing but a Adapter
public interface ThirdPartyProductServiceClient {

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException;
    public FakeStoreProductDTO createProduct(GenericProductDTO product);
    public List<FakeStoreProductDTO> getAllProducts();
    public FakeStoreProductDTO deleteProduct(Long id);

}
