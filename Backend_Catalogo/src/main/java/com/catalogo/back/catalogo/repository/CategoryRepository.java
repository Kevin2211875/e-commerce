package com.catalogo.back.catalogo.repository;

import com.catalogo.back.catalogo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByName(String name);
}
