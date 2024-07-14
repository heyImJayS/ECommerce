package dev.jays.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;

    @Column(length = 4000)
    @Lob
    private String description;

    private String image;    //We store URL of the image
    private double price;
    private String brand;

    // Product :  Category
    // P -> C =>  1   :    1        <- One product can have one category
    // P <- C =>  M   :    1        <- One Category can have Multiple products
    // Ans M:1

    //We are removing this Category because its falles in infinite loop. So we created ProductDto
    @ManyToOne(cascade= {CascadeType.PERSIST})
    @JoinColumn(name= "category_id")
    private Category category;     //Relation

    // Product : Price
    // Product -> price => 1 : 1   //A product have one price tag
    // Product <- Price => 1 : 1   //For a price there can be one product

    //@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch= FetchType.LAZY)
    //@Fetch(FetchMode.JOIN)
    private String currency;
    private String availability;
}
