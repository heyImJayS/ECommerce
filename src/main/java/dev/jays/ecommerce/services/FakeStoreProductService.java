package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.FakeStoreProductDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("FakeStoreProductServiceImplementation")
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestURL= "https://fakestoreapi.com/products/{id}";
    private String productCreationRequestURL="https://fakestoreapi.com/products";

    public  FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }


    @Override
    public GenericProductDTO getProductById(Long id) {
        // RestTemplate a library in Spring Boot, It allows to call the 3rd party APIs and get the data from them and play around
        RestTemplate restTemplate=restTemplateBuilder.build();
        //Here all the data from the API get stored in the response variable
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDTO.class, id);   // THis is as per the order of the variables in the URL, as URL got only ID, so we wrote id in the parameters
        //ResponseEntity has lots of functionality, like getting the Http Response Codes
        //response.getStatusCode();

        FakeStoreProductDTO fakeStoreProductDTO= response.getBody();
        //Product product= new Product();

        GenericProductDTO product= new GenericProductDTO();
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());

        return product;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {

        /*
        THIRD PARTY API for create of Product:-
        fetch('https://fakestoreapi.com/products',{
            method:"POST",
            body:JSON.stringify(
                {
                    title: 'test product',
                    price: 13.5,
                    description: 'lorem ipsum set',
                    image: 'https://i.pravatar.cc',
                    category: 'electronic'
                }
            )
        })
            .then(res=>res.json())
            .then(json=>console.log(json))

            */
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response= restTemplate.postForEntity(productCreationRequestURL, product, GenericProductDTO.class);
        return response.getBody();
    }
}
