package com.catalogo.back.catalogo.repository;

import com.catalogo.back.catalogo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, ProductCustomRepository {
    List<Product> findByCategory(String category);
}
