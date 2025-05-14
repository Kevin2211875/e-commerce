package com.catalogo.back.catalogo.controller;

import com.catalogo.back.catalogo.DTO.ProductFilter;
import com.catalogo.back.catalogo.model.Product;
import com.catalogo.back.catalogo.model.Review;
import com.catalogo.back.catalogo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public List<Product> listProducts() {
        return productService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product existing = productService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (product.getName() != null) {existing.setName(product.getName());}
            if (product.getDescription() != null) {existing.setDescription(product.getDescription());}
            if (product.getImage() != null) {existing.setImage(product.getImage());}
            if (product.getCategory() != null) {existing.setCategory(product.getCategory());}
            if (product.getBrand() != null) {existing.setBrand(product.getBrand());}
            if (product.getPrice() != 0) {existing.setPrice(product.getPrice());}
            if (product.getQuantity() != 0) {existing.setQuantity(product.getQuantity());}
            if (product.getInternalNotes() != null) {existing.setInternalNotes(product.getInternalNotes());}
            if (product.getSuggestions() != null) {existing.setSuggestions(product.getSuggestions());}
            if (product.getReviews() != null) {existing.setReviews(product.getReviews());}
            if (product.getAttributes() != null) {existing.setAttributes(product.getAttributes());}
            if (product.isAvailable() != existing.isAvailable()) {existing.setAvailable(product.isAvailable());}
            existing.setUpdateDate(new Date());
            productService.updateProduct(existing);
            return ResponseEntity.ok().body("El producto se ha actualizado correctamente");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("El producto se ha eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list/{category}")
    public List<Product> listByCategory(@PathVariable String category) {
        return productService.listByCategory(category);
    }

//Crud Reviews
    // Get all reviews for a product
    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable String productId) {
        List<Review> reviews = productService.getProductReviews(productId);
        return reviews != null ? ResponseEntity.ok(reviews) : ResponseEntity.notFound().build();
    }

    // Add a review to a product
    @PostMapping("/{productId}/reviews")
    public ResponseEntity<Product> addReview(@PathVariable String productId, @RequestBody Review review) {
        Product updatedProduct = productService.addReview(productId, review);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    // Update a review for a product
    @PutMapping("/{productId}/reviews/{userId}")
    public ResponseEntity<Product> updateReview(
            @PathVariable String productId,
            @PathVariable String userId,
            @RequestBody Review updatedReview) {
        Product updatedProduct = productService.updateReview(productId, userId, updatedReview);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    // Delete a review for a product
    @DeleteMapping("/{productId}/reviews/{userId}")
    public ResponseEntity<Product> deleteReview(@PathVariable String productId, @PathVariable String userId) {
        Product updatedProduct = productService.deleteReview(productId, userId);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

// internalNotes methods
    // Update internalNotes
    @PutMapping("/{productId}/internal-notes")
    public ResponseEntity<Product> updateInternalNotes(@PathVariable String productId, @RequestBody String internalNote) {
        Product updatedProduct = productService.updateInternalNotes(productId, internalNote);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    // Delete internalNotes
    @DeleteMapping("/{productId}/internal-notes")
    public ResponseEntity<Product> deleteInternalNotes(@PathVariable String productId) {
        Product updatedProduct = productService.deleteInternalNotes(productId);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }
    //Filtros por lo que quiera sumerce
    @PostMapping("/filter")
    public List<Product> filterProduct(@RequestBody ProductFilter filter) {
        return productService.filterProduct(filter);
    }

    //Obteener filtros especificos del producto de cara a la experiencia de usuario
    //para que pueda visualizar los filtros en el frontend segun el producto al que ingresa
    @GetMapping("/getFilters")
    public Map<String, List<Object>> getProductFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String term) {

        List<Product> products;

        if (category != null) {
            products = productService.listByCategory(category);
        } else if (term != null) {
            ProductFilter filter = new ProductFilter();
            filter.setName(term);
            products = productService.filterProduct(filter);
        } else {
            products = productService.listAll();
        }

        Map<String, List<Object>> filters = new HashMap<>();

        for (Product product : products) {
            if (product.getAttributes() != null) {
                for (Map.Entry<String, Object> entry : product.getAttributes().entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    filters.computeIfAbsent(key, k -> new ArrayList<>());
                    if (!filters.get(key).contains(value)) {
                        filters.get(key).add(value);
                    }
                }
            }
        }

        return filters;
    }
}