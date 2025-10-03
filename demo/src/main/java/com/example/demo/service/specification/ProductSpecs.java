package com.example.demo.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.domain.Product;
import com.example.demo.domain.Product_;

public class ProductSpecs {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> minPrice(double min) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Product_.PRICE), min);
    }

    public static Specification<Product> maxPrice(double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Product_.PRICE), max);
    }

    public static Specification<Product> factoryLike(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.FACTORY), "%" + factory + "%");
    }

    public static Specification<Product> targetLike(String target) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.TARGET), "%" + target + "%");
    }

    public static Specification<Product> factoryMapping(List<String> factories) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factories);
    }

    public static Specification<Product> priceBetween(double minPrice, double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.ge(root.get(Product_.PRICE), minPrice),
                criteriaBuilder.le(root.get(Product_.PRICE), maxPrice));
    }

}
