package dev.jays.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;    //We store URL of the image
    private Category category;
    private double price;
}
