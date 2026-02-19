package org.skypro.skyshop.model.service;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService){
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductInBasket(UUID id){
        System.out.println("🟢 BasketService получил запрос на добавление ID: " + id);
        if (storageService.getProductById(id).isEmpty()){
            throw new NullPointerException("Продукт с ID " + id + " не найден");
        }
        else{productBasket.addProduct(id);}
    }

    public UserBasket getUserBasket(){
        return new UserBasket(productBasket.getBasket().entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).get(), entry.getValue()))
                .collect(Collectors.toList()));
    }
}
