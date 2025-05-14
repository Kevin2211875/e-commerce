package com.catalogo.back.catalogo.DTO;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ProductFilter {
    private String name;
    private String category;
    private String brand;
    private Double minPrice;
    private Double maxPrice;
    private Boolean available;
    private Date fromDate;
    private Date toDate;
    private Map<String, Object> attributes;
    private Map<String, Range<Double>> attributeRanges;

    @Data
    public static class Range<T> {
        private T min;
        private T max;
    }
}