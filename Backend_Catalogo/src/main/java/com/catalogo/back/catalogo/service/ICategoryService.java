package com.catalogo.back.catalogo.service;

import com.catalogo.back.catalogo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(String id);
    Category save(Category category);
    void delete(Category category);
    Category findByName(String name);
}
