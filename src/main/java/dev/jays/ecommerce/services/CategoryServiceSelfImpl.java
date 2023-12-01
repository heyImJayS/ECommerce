package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericCategoryDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Category;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.repositories.CategoryRepository;
import dev.jays.ecommerce.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
 @Service("CategoryServiceImplementation")
public class CategoryServiceSelfImpl implements CategoryServiceSelf {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceSelfImpl(CategoryRepository categoryRepository,
                                   ProductRepository productRepository){
        this.categoryRepository= categoryRepository;
        this.productRepository = productRepository;
    }

    public static GenericCategoryDTO convertCategoryToGenericCategoryDTO(Category category){
        GenericCategoryDTO genericCategoryDTO= new GenericCategoryDTO();
        genericCategoryDTO.setName(category.getName());
        return genericCategoryDTO;
    }
    @Override
    public List<GenericProductDTO> getCategoryProducts(String uuid) {
        Optional<Category> optionalCategory= categoryRepository.findById(UUID.fromString(uuid));
        //Because of Optional<> we have to handle the
        if(optionalCategory.isEmpty()){
            throw new NullPointerException("Empty Category Returned");
        }
        Category category= optionalCategory.get();
        List<Product> products = category.getProducts();
        List<GenericProductDTO> res= new ArrayList<>();
        for(Product product: products){
            res.add(ProductServiceSelfImpl.convertProductToGenericProductDTO(product));
        }
        return res;
    }

    //This will give the Products of the UUID of single Category
    @Override
    public List<String> getProductTitles(String categoryUuid){
        List<String> titles= new ArrayList<>();
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(categoryUuid));
        if(categoryOptional.isEmpty()){
            throw new NullPointerException("Empty Categoty");
        }
        Category category= categoryOptional.get();

        category.getProducts().forEach(product -> titles.add(product.getTitle()));
        return titles;
    }

    @Override
    public List<String> getAllCategoryProductTitles(List<String> categoryUUIDs){

        List<UUID> uuids= new ArrayList<>();
        for(String uuid: categoryUUIDs){
            uuids.add(UUID.fromString(uuid));
        }
        /*
        //This is how ordinay people write code

        List<Category> categories= categoryRepository.findAllById(uuids);
        if(categories.isEmpty()){
            throw new NullPointerException("Empty Categoty");
        }
        List<String> titles = new ArrayList<>();

        for(Category category: categories){
            category.getProducts().forEach(product -> titles.add(product.getTitle()));
        }
        */

        //Resolution of N+1 Problem
        List<Category> categories= categoryRepository.findAllById(uuids);
        List<Product> products = productRepository.findAllByCategoryIn(categories);
        List<String> titles= new ArrayList<>();
        for(Product product: products){
            titles.add(product.getTitle());
        }
        return titles;
    }

    @Override
    public List<GenericCategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<GenericCategoryDTO> res= new ArrayList<>();
        for(Category category: categories){
            res.add(convertCategoryToGenericCategoryDTO(category));
        }
        return res;
    }
}
