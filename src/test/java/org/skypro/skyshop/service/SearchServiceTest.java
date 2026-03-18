package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageServiceMock;

    @InjectMocks
    private SearchService searchService;


    @Test
    void ifStorageIsEmpty (){
        String requiredProduct = "iPhone";
        when(storageServiceMock.getAllSearchable()).thenReturn(Collections.emptyList());
        Collection<SearchResult> searchResults = searchService.search(requiredProduct);

        assertThat(searchResults.isEmpty());
    }

    @Test
    void notEmptyButNoMatches(){
        String requiredProduct = "iPhone";
        List<Searchable> searchables = Arrays.asList(
                new SimpleProduct("Samsung TV", 50000),
                new SimpleProduct("MacBook", 100000),
                new Article("Как готовить", "Рецепты")
        );
        when(storageServiceMock.getAllSearchable()).thenReturn(searchables);
        Collection<SearchResult> searchResults = searchService.search(requiredProduct);

        assertThat(searchResults.isEmpty());
    }

    @Test
    void notEmptyNaveMatches(){
        String requiredProduct = "iPhone";
        List<Searchable> searchables = Arrays.asList(
                new SimpleProduct("iPhone", 130000),
                new SimpleProduct("MacBook", 100000),
                new Article("Как готовить", "Рецепты")
        );
        when(storageServiceMock.getAllSearchable()).thenReturn(searchables);
        Collection<SearchResult> searchResults = searchService.search(requiredProduct);

        assertThat(searchResults.isEmpty());
    }

}
