package dev.jays.ecommerce.controllers;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.exceptions.NotFoundException;
import dev.jays.ecommerce.services.ProductServiceSelf;
import dev.jays.ecommerce.thirdPartyClients.productService.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductControllerTest {
    /*
    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Autowired
    private ProductController productController;
    @MockBean
    private ProductServiceSelf productServiceSelf;

    @Test
    void returnNullWhenAllProductDoesntExist() throws NotFoundException{      //@PathVariable says that the id in "Long id" is nothing but same id in Path URL.
        GenericProductDTO genericProductDTO= productController.getProductByID("Empty Token", UUID.fromString("fe22745d-f83a-4334-a0c3-d49f1a6c40e2"));
        //Here instead of ProductService being a real productService object. We are using the Mock object
        when(productServiceSelf.getProductById(UUID.fromString("fe22745d-f83a-4334-a0c3-d49f1a6c40e2")))
                .thenReturn(null);

        assertNull(genericProductDTO);
    }

    @Test
    void throwExceptionWhenProductDoesntExist() throws NotFoundException{
        //1st we mocked all the dependency
        when(productServiceSelf.getProductById(any(UUID.class))
        ).thenReturn(new GenericProductDTO());

        //Now we trying to test the required function
        assertThrows(NotFoundException.class, ()-> productController.getProductByID("Empty Token", UUID.fromString("fe22745d-f83a-4334-a0c3-d49f1a6c40e2")));
    }

    @Test
    void testProduct() throws NotFoundException {
        assertNull(fakeStoreProductServiceClient.getProductById(101L));
        assertThrows(NotFoundException.class, () -> fakeStoreProductServiceClient.getProductById(101L));
    }

    @Test
    @DisplayName("1 + 1 equals 2")
    void testOnePlusOneEqualsTrue(){
        assert(11 == 1+1);
        assertEquals(11,1+1, "Wrong  operation");

    }
    @Test
    void additionShouldBeCorrect(){
        assert -1+ -1 == -2;

    }

     */

}
