package dev.jays.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO {
    //This class will be responsible for tranfer the data to FakeStore API
   /*
   fetch('https://fakestoreapi.com/products/1')
            .then(res=>res.json())
            .then(json=>console.log(json))

     output
            {
                id:1,
                title:'...',
                price:'...',
                category:'...',
                description:'...',
                image:'...'
            }
       */
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;    //Normally URL is String
}
