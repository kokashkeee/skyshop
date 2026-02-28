package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
@Service
public class ProductBasket {
    private final Map<UUID, Integer> basket;
    private final UUID id;

    public ProductBasket() {
        this.basket = new HashMap<>();
        this.id = UUID.randomUUID();
    }

    public void addProduct(UUID id){
        Integer currentCount = basket.computeIfAbsent(id, k -> 0);
        basket.put(id, currentCount + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
