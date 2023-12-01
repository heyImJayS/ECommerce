package dev.jays.ecommerce.repositories;


import dev.jays.ecommerce.models.Category;
import dev.jays.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    Optional<Category> findById(UUID uuid);
    @Override
    List<Category> findAllById(Iterable<UUID> uuids);
    //This will return all the category records in The Category Table
    @Override
    List<Category> findAll();
}
