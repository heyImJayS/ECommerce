package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericCategoryDTO;
import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Category;

import java.util.List;

public interface CategoryServiceSelf {
    public List<GenericProductDTO> getCategoryProducts(String uuid);
    public List<String> getProductTitles(String uuid);
    public List<String> getAllCategoryProductTitles(List<String> categoryUUIDs);
    public List<GenericCategoryDTO> getAllCategories();
}
