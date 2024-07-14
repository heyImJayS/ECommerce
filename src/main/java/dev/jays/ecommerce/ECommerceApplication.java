package dev.jays.ecommerce;

import dev.jays.ecommerce.repositories.CategoryRepository;
import dev.jays.ecommerce.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ECommerceApplication(ProductRepository productRepository,
                                CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    //@Transactional()
    //@Override
    public void run(String... args) throws Exception {
/*

        Category category= new Category();
        category.setName("Vivo Products");
        Category savedCategory = categoryRepository.save(category);   // If we set cascade in Product Class to Category field then we do not need to save to repo 1st. It will be autometically be saved.

        Currency currency = new Currency();
        currency.setCountryCurrency("Rupee");
        Currency savedCurrency = currencyRepository.save(currency);

        Product product = new Product();
        product.setTitle("Vivo V29");
        product.setPrice(32999.80);
        product.setCurrency(savedCurrency);
        product.setDescription("Best Mid Range Phones");
        product.setCategory(savedCategory);
        product.setImage("google.com/vivo_v29");
        productRepository.save(product);
*/

        /*
        Product product1 = new Product();
        product1.setTitle("Apple Iphone 15 128GB");
        product1.setPrice(52000.55);
        product1.setCurrency(savedCurrency);
        product1.setDescription("Middleclass Phone");
        product1.setCategory(savedCategory);
        product1.setImage("google.com/iphone15");
        productRepository.save(product1);
         */

        /*
        for(Product p: category.getProducts()){
            System.out.println(p);
        }
        */
    }

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

}
