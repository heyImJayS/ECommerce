package dev.jays.ecommerce.dtos;

import dev.jays.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GenericProductDTO {
    //We made this Class, only because tomorrow may happen like FakeStore API might return a different object.
    //We dont want FakeStoreDTO completely dependent API object.
    //FakeStoreProductDTO we got from the

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;    //Normally URL is String

    public static GenericProductDTO convertProductToGenericProductDTO(Product product){
        GenericProductDTO genericProductDTO= new GenericProductDTO();
        if(product.getCategory()!=null){
            genericProductDTO.setCategory(product.getCategory().getName());
        }
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setPrice(product.getPrice());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setDescription(product.getDescription());
        return genericProductDTO;
    }
}
