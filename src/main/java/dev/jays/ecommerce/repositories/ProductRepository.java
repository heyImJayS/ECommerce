package dev.jays.ecommerce.repositories;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Category;
import dev.jays.ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    <S extends Product> S save(S entity);

    //Example on SQL Queries using JPA Queries
    //This will find and return the product with Title titl
    Product findByTitleEquals(String titl);
    //This will find and return the Product having Title titl

    //Product findByTitleEqualsAndCurrency_CountryCurrencyEquals(String titl, String currency);   //Price is the Object attribute inside Title Class. Underscore specifies the attribute inside Price Object
    //This will find all the Products with provided currency
    //List<Product> findAllByCurrency_CountryCurrency(String currency);

    //Customized Query self query
    //@Query <- Says that Spring JPA won't act upon the function. You can specify your own customized query.

    //@Query(value="select * from product where title= :xyz", nativeQuery = true)
    @Query(value= CustomQueries.FIND_ALL_PRODUCTS_BY_TITLE, nativeQuery = true)
    List<Product> getAllProductsByTitle(String xyz);

    //Below is using Hibernate Query Language. Which is nothing but the DB language independent. Means if in future the DB changes then the query we dont have to modify. Hibernate will autometically understands the query.
    //But in case of the above, its completely dependent upon the DB language i.e, MySQL.
    //for more details Hibernate Query Language please follow:- https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm
    //Hibernate Query Language more specific to Class and Attribute names not the table names.
    List<Product> readAllByTitleLike(String xyz);

    @Override
    List<Product> findAllById(Iterable<UUID> uuids);

    //@Query("select Product from Product where Product.category.uuid in :uuids")
    //List<Product> findAllByCategoryIn(List<UUID> uuids);

    List<Product> findAllByCategoryIn(List<Category> categories);

    //This will give all the Products in the Product Repository
    @Override
    List<Product> findAll();
    @Override
    Optional<Product> findById(UUID uuid);
    //@Query(value ="select * from product where price BETWEEN (:startPrice, :endPrice)", nativeQuery = true)
    @Query(value = "SELECT * FROM product WHERE price BETWEEN :startPrice AND :endPrice", nativeQuery = true)
    Page<Product> findProductInPriceRange(@Param("startPrice") Double startPrice,
                                     @Param("endPrice") Double endPrice,
                                     Pageable pageable);

}
