package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.thirdPartyClients.productService.fakestore.FakeStoreProductDTO;
import dev.jays.ecommerce.thirdPartyClients.productService.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("FakeStoreProductServiceImplementation")
public class FakeStoreProductService implements ProductService {

    private GenericProductDTO convertFakeStoreProductDTOIntoGenericProductDTO( FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO product= new GenericProductDTO();
        product.setId(fakeStoreProductDTO.getId());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        return product;
    }

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public  FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException{
        return convertFakeStoreProductDTOIntoGenericProductDTO(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return convertFakeStoreProductDTOIntoGenericProductDTO(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> res= new ArrayList<>();
        for(FakeStoreProductDTO i: fakeStoreProductServiceClient.getAllProducts()){
            res.add(convertFakeStoreProductDTOIntoGenericProductDTO(i));
        }
        return res;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return convertFakeStoreProductDTOIntoGenericProductDTO(fakeStoreProductServiceClient.deleteProduct(id));
    }
}
