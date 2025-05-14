package com.catalogo.back.catalogo.service;

import com.catalogo.back.catalogo.model.Product;
import com.catalogo.back.catalogo.model.Review;

import java.util.List;

public interface IProductService {
    List<Product> listAll();
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    List<Product> listByCategory(String category);

    //Crud Review
    List<Review> getProductReviews(String productId);
    Product addReview(String productId, Review review);
    Product updateReview(String productId, String userId, Review updatedReview);
    Product deleteReview(String productId, String userId);


}
