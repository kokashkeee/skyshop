package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private String title;
    private String text;

    public Article(String title, String text){
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
    }

    public UUID getId(){
        return id;
    }

    @Override
    public String toString(){
        return title + "\n" + text;
    }

    public String getSearchTerm(){
        return toString();
    }

    @JsonIgnore
    public String getSearchType(){
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
