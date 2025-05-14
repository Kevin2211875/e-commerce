package com.catalogo.back.catalogo.service;

import com.catalogo.back.catalogo.DTO.ProductFilter;
import com.catalogo.back.catalogo.model.Product;
import com.catalogo.back.catalogo.model.Review;
import com.catalogo.back.catalogo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());

        if (existingProduct.isPresent()) {
            Product updated = existingProduct.get();
            updated.setName(product.getName());
            updated.setDescription(product.getDescription());
            updated.setImage(product.getImage());
            updated.setCategory(product.getCategory());
            updated.setBrand(product.getBrand());
            updated.setPrice(product.getPrice());
            updated.setUpdateDate(product.getUpdateDate());
            updated.setQuantity(product.getQuantity());
            updated.setAvailable(product.isAvailable());
            updated.setInternalNotes(product.getInternalNotes());
            updated.setAttributes(product.getAttributes());
            return productRepository.save(updated);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public List<Product> listByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Get all reviews for a product
    public List<Review> getProductReviews(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(Product::getReviews).orElse(null);
    }

    // Add a review to a product
    public Product addReview(String productId, Review review) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.getReviews().add(review);
            return productRepository.save(product);
        }
        return null;
    }

    // Update a review
    public Product updateReview(String productId, String userId, Review updatedReview) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<Review> reviews = product.getReviews();

            for (Review review : reviews) {
                if (review.getUserId().equals(userId)) {
                    review.setRating(updatedReview.getRating());
                    review.setComment(updatedReview.getComment());
                    review.setDate(updatedReview.getDate());
                    return productRepository.save(product);
                }
            }
        }
        return null;
    }

    // Delete a review
    public Product deleteReview(String productId, String userId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<Review> reviews = product.getReviews();

            boolean removed = reviews.removeIf(review -> review.getUserId().equals(userId));
            if (removed) {
                return productRepository.save(product);
            }
        }
        return null;
    }

// Internal Notes
    // Add or Update internalNotes
    public Product updateInternalNotes(String productId, String internalNote) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setInternalNotes(internalNote);
            return productRepository.save(product);
        }
        return null;
    }

    // Delete internalNotes
    public Product deleteInternalNotes(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setInternalNotes(null);
            return productRepository.save(product);
        }
        return null;
    }
    //Filtrar productos
    public List<Product> filterProduct(ProductFilter filter) {
        return productRepository.findByFilters(filter);
    }
}
