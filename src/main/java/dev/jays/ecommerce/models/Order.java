package dev.jays.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModel{
    //Order:product
    // O -> P => 1 : M
    // O <- P => M : 1
    //Ans     => M: M
    @ManyToMany
    @JoinTable(name= "order_products",
            joinColumns = @JoinColumn(name="order_ID"),
            inverseJoinColumns = @JoinColumn(name="product_ID"))
    //joinColumns => current Class Id name
    //inverseJoinColumns => Joining table Id name
    private List<Product> products;

}

