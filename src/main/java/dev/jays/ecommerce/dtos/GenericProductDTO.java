package dev.jays.ecommerce.dtos;

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
}
