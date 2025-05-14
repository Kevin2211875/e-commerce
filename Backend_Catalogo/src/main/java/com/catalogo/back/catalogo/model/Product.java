package com.catalogo.back.catalogo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document (collection = "product")
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private String image;
    private String category;
    private String brand;
    private double price;
    private Date publishDate;
    private Date updateDate;
    private int quantity;
    private boolean available;
    private String internalNotes;
    private List<Review> reviews;
    private List<String> suggestions;
    private Map<String, Object> attributes; // Filtros específicos por categoría, Hay que actualizar Backend
}
