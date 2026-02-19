package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService(Map<UUID, Product> productStorage, Map<UUID, Article> articleStorage){
        this.articleStorage = new HashMap<>();
        this.productStorage = new HashMap<>();
        addSmth();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }



    private void addSmth(){
        Article article = new Article("Купил 'Молоко' и сру три метра против ветра!", "Надоумил же меня Бог купить молоко на сайте, предоставляющем услуги обучения...");
        Product product1 = new SimpleProduct("Сыр", 200);
        productStorage.put(product1.getId(),product1);
        articleStorage.put(article.getId(),article);
    }

    public Collection<Product> getProductStorage(){
        return Collections.unmodifiableCollection(productStorage.values());
    }

    public Collection<Article> getArticleStorage(){
        return Collections.unmodifiableCollection(articleStorage.values());
    }

    public Collection<Searchable> getAllSearchable(){
        Collection<Searchable> result = new ArrayList<>();
        result.addAll(productStorage.values());
        result.addAll(articleStorage.values());
        return result;
    }
}
