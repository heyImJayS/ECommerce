package dev.jays.ecommerce.repositories;

public interface CustomQueries {
    final String FIND_ALL_PRODUCTS_BY_TITLE= "select * from product where title= :xyz";
}
