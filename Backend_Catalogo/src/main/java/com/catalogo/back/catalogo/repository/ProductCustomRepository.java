package com.catalogo.back.catalogo.repository;

import com.catalogo.back.catalogo.DTO.ProductFilter;
import com.catalogo.back.catalogo.model.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findByFilters(ProductFilter filter);
}
