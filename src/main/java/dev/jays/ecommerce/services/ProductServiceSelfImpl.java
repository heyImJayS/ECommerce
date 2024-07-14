package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.elasticrepos.ProductElasticSearchRepo;
import dev.jays.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service("ProductServiceImplementation")
public class ProductServiceSelfImpl implements ProductServiceSelf {
    private ProductRepository productRepository;
    private ProductElasticSearchRepo productElasticSearchRepo;
    public ProductServiceSelfImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDTO getProductById(UUID productUUID) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productUUID);
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Empty Product returned from getProductById()");
        }
        Product product= optionalProduct.get();
        return GenericProductDTO.convertProductToGenericProductDTO(product);
    }

    @Override
    public GenericProductDTO createProduct(Product product) {
        //Saving into MySql DB
        productRepository.save(product);
        //Saving Product into ElasticSearch DB as well
        //productElasticSearchRepo.save(product);
        return GenericProductDTO.convertProductToGenericProductDTO(product);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> res = new ArrayList<>();
        List<Product> allProducts= productRepository.findAll();
        for(Product product: allProducts){
            res.add(GenericProductDTO.convertProductToGenericProductDTO(product));
        }
        return res;
    }
    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return null;
    }
}
