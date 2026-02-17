package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(String name, String contentType, UUID id){
        this.name = name;
        this.contentType = contentType;
        this.id = id;
    }

    public static SearchResult fromSearchable(Searchable searchable){
        UUID id = searchable.getId();
        String name = searchable.getSearchTerm();
        String contentType = searchable.getSearchType();
        return new SearchResult(contentType, name, id);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}
