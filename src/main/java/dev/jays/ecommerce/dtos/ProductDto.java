package dev.jays.ecommerce.dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//We have created this class because product class has one Category attribute that causing the Cyclic Dependency
public class ProductDto {

    private String title;
    private String description;
    private String image;    //We store URL of the image

    //@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    private String currency;

    private double price;
}
