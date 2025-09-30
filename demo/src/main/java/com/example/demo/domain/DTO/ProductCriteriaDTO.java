package com.example.demo.domain.DTO;

import java.util.Optional;

public class ProductCriteriaDTO {
    private Optional<String> factories;
    private Optional<String> targets;
    private Optional<String> prices;
    private Optional<String> sort;

    public Optional<String> getFactories() {
        return factories;
    }

    public void setFactories(Optional<String> factories) {
        this.factories = factories;
    }

    public Optional<String> getTargets() {
        return targets;
    }

    public void setTargets(Optional<String> targets) {
        this.targets = targets;
    }

    public Optional<String> getPrices() {
        return prices;
    }

    public void setPrices(Optional<String> prices) {
        this.prices = prices;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }
}
