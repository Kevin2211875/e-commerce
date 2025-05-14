package com.catalogo.back.catalogo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private String userId;
    private String userName;
    private int rating;
    private String comment;
    private LocalDateTime date = LocalDateTime.now();
}
