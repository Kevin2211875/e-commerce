package com.catalogo.back.catalogo.repository;

import com.catalogo.back.catalogo.DTO.ProductFilter;
import com.catalogo.back.catalogo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> findByFilters(ProductFilter filter) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            criteriaList.add(Criteria.where("name").regex(filter.getName(), "i"));
        }

        if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
            criteriaList.add(Criteria.where("category").is(filter.getCategory()));
        }

        if (filter.getBrand() != null && !filter.getBrand().isEmpty()) {
            criteriaList.add(Criteria.where("brand").is(filter.getBrand()));
        }

        if (filter.getMinPrice() != null) {
            criteriaList.add(Criteria.where("price").gte(filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            criteriaList.add(Criteria.where("price").lte(filter.getMaxPrice()));
        }

        if (filter.getAvailable() != null) {
            criteriaList.add(Criteria.where("available").is(filter.getAvailable()));
        }

        if (filter.getFromDate() != null || filter.getToDate() != null) {
            Criteria dateCriteria = Criteria.where("publishDate");
            if (filter.getFromDate() != null && filter.getToDate() != null) {
                dateCriteria.gte(filter.getFromDate()).lte(filter.getToDate());
            } else if (filter.getFromDate() != null) {
                dateCriteria.gte(filter.getFromDate());
            } else {
                dateCriteria.lte(filter.getToDate());
            }
            criteriaList.add(dateCriteria);
        }

        if (filter.getAttributes() != null && !filter.getAttributes().isEmpty()) {
            for (Map.Entry<String, Object> entry : filter.getAttributes().entrySet()) {
                criteriaList.add(Criteria.where("attributes." + entry.getKey()).is(entry.getValue()));
            }
        }

        if (filter.getAttributeRanges() != null && !filter.getAttributeRanges().isEmpty()) {
            for (Map.Entry<String, ProductFilter.Range<Double>> entry : filter.getAttributeRanges().entrySet()) {
                ProductFilter.Range<Double> range = entry.getValue();
                Criteria rangeCriteria = Criteria.where("attributes." + entry.getKey());
                if (range.getMin() != null && range.getMax() != null) {
                    rangeCriteria.gte(range.getMin()).lte(range.getMax());
                } else if (range.getMin() != null) {
                    rangeCriteria.gte(range.getMin());
                } else {
                    rangeCriteria.lte(range.getMax());
                }
                criteriaList.add(rangeCriteria);
            }
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query, Product.class);
    }
}
