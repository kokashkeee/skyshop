//package org.skypro.skyshop.model.basket;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//public class ProductBasket {
//    private final Map<UUID, Integer> basket = new HashMap<>();
//
//    public void addProduct(UUID id){
//        if (basket.containsKey(id)) {
//            Integer currentCount = basket.get(id);
//            basket.put(id, currentCount + 1);
//        } else {
//            basket.put(id, 1);
//        }
//    }
//
//    public Map<UUID, Integer> getBasket() {
//        return Collections.unmodifiableMap(basket);
//    }
//}
