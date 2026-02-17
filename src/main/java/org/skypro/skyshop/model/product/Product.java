package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected String name;
    protected String message = "неверно указано имя товара";
    protected UUID id;

    public Product(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("неверно указано имя товара");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public UUID getId(){
        return id;
    }

    public abstract boolean isSpecial();

    public abstract int getPrice();

    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }

    public String getSearchType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}