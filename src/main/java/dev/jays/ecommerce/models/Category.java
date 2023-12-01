package dev.jays.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel{
    private String name;
    //Category : Product
    // C -> P => 1 : M
    // P -> C => 1 : 1
    // 1 : M
    @OneToMany(mappedBy = "category")    //Mapped By used to tell that, there exist same relation with entity named 'category' in other class(Product). So, Do not consider as two different relations, both are same.
    @Fetch(FetchMode.SELECT)
    private List<Product> products;
}
