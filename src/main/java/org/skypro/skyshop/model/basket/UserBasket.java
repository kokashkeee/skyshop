package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private final int total;

    public UserBasket(List<BasketItem> basketItemList){
        this.basketItemList = basketItemList;
        this.total = basketItemList.stream()
                .mapToInt(basketItem -> (basketItem.getQuantity())*(basketItem.getProduct().getPrice()))
                .sum();
    }
}
