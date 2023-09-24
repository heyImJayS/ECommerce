package dev.jays.ecommerce.models;

public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;    //We store URL of the image
    private Category category;
    private double price;
    private int quantity;

}
