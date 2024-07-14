package dev.jays.ecommerce.elasticrepos;

import dev.jays.ecommerce.models.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProductElasticSearchRepo{ //extends ElasticsearchRepository<Product, UUID> {

}
