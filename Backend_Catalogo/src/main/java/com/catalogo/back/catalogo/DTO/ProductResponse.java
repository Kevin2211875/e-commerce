package com.catalogo.back.catalogo.DTO;

import lombok.Data;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Double averageRating;
    private Boolean available;
}
