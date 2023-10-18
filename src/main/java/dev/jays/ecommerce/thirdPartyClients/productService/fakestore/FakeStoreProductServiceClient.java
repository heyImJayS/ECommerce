package dev.jays.ecommerce.thirdPartyClients.productService.fakestore;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.thirdPartyClients.productService.ThirdPartyProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//This is the Client Class which will directly communicate with the APIs directly.
//This Client Class is nothing but wrapper around the FakeStore APIs

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
//    private String getProductRequestURL= "https://fakestoreapi.com/products/{id}";
//    private String productCreationRequestURL= "https://fakestoreapi.com/products";
//    private String getAllProductsRequestURL= "https://fakestoreapi.com/products";
//    private String deleteProductRequestURL= "https://fakestoreapi.com/products/{id}";

    @Value("${fakestore.api.url}")
    private String fakeStoreApiURL;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductApiPath;

    //Below commented might not work ..we need to initialize the way we did it in the constructor. Because it might led to nullify of values as it might late while fetching value from application.properties file. So, compiler autometically fill the values with Null
//    private String getProductRequestURL= fakeStoreApiURL + fakeStoreProductApiPath + "/{id}";
//    private String productCreationRequestURL= fakeStoreApiURL + fakeStoreProductApiPath ;
//    private String getAllProductsRequestURL= fakeStoreApiURL + fakeStoreProductApiPath;
//    private String deleteProductRequestURL= fakeStoreApiURL + fakeStoreProductApiPath + "/{id}";

    private String getProductRequestURL;
    private String productCreationRequestURL;
    private String getAllProductsRequestURL;
    private String deleteProductRequestURL;


    public  FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder ,
                                          @Value("${fakestore.api.url}") String fakeStoreApiURL,
                                          @Value("${fakestore.api.paths.product}") String fakeStoreProductApiPath){

        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductRequestURL= fakeStoreApiURL + fakeStoreProductApiPath + "/{id}";
        this.productCreationRequestURL= fakeStoreApiURL + fakeStoreProductApiPath ;
        this.getAllProductsRequestURL= fakeStoreApiURL + fakeStoreProductApiPath;
        this.deleteProductRequestURL= fakeStoreApiURL + fakeStoreProductApiPath + "/{id}";
    }

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
    @Override
    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException{
        // RestTemplate a library in Spring Boot, It allows to call the 3rd party APIs and get the data from them and play around
        RestTemplate restTemplate = restTemplateBuilder.build();
        //Here all the data from the API get stored in the response variable
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDTO.class, id);   // THis is as per the order of the variables in the URL, as URL got only ID, so we wrote id in the parameters
        //ResponseEntity has lots of functionality, like getting the Http Response Codes
        //response.getStatusCode();

        FakeStoreProductDTO fakeStoreProductDTO= response.getBody();
        //Product product= new Product();

        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product having ID: "+ id +" couldn't found.");
        }
        return fakeStoreProductDTO;
    }

    @Override
    public FakeStoreProductDTO createProduct(GenericProductDTO product) {

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
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(productCreationRequestURL, product, FakeStoreProductDTO.class);
        return response.getBody();
    }

    @Override
    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(getAllProductsRequestURL, FakeStoreProductDTO[].class);          //This one is important how FakeStoreProductDTO[].class is taken
//        List<GenericProductDTO> res= new ArrayList<>();
//        for(FakeStoreProductDTO x: Arrays.stream(response.getBody()).toList()){
//            res.add(convertFakeStoreProductDTOIntoGenericProductDTO(x));
//        }
        return Arrays.stream(response.getBody()).toList();
    }

    @Override
    public FakeStoreProductDTO deleteProduct(Long id) {
        /*
        fetch('https://fakestoreapi.com/products/6',{
                method:"DELETE"
        })
        .then(res=>res.json())
        .then(json=>console.log(json))

        //output
        {
            id:6,
                    title:'...',
                price:'...',
                category:'...',
                description:'...',
                image:'...'
        }
        */
        RestTemplate restTemplate = restTemplateBuilder.build();
        //As we check and found that, there is no return type for the delete() method available in RestTemplate.
        //restTemplate.delete(deleteProductRequestURL, );
        //So, we go for the getForEntity() code and copied it
        RequestCallback requestCallback= restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response =  restTemplate.execute(deleteProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor,id );

        //Now we convert
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        return fakeStoreProductDTO;
    }
}
