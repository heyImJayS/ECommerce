package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service("ProductServiceImplementation")
public class ProductServiceSelfImpl implements ProductServiceSelf {
    private ProductRepository productRepository;

    public ProductServiceSelfImpl(ProductRepository productRepository){
        this.productRepository= productRepository;
    }
    public static GenericProductDTO convertProductToGenericProductDTO(Product product){
        GenericProductDTO genericProductDTO= new GenericProductDTO();
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setPrice(product.getPrice());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setDescription(product.getDescription());

        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(UUID productUUID) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productUUID);
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Empty Product returned from getProductById()");
        }
        Product product= optionalProduct.get();
        return convertProductToGenericProductDTO(product);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> res = new ArrayList<>();
        for(Product product: productRepository.findAll()){
            res.add(convertProductToGenericProductDTO(product));

        }
        return res;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return null;
    }
}
