package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addNonExistentProduct(){
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> basketService.addProductInBasket(invalidId));
        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void addExistentProduct(){
        UUID validId = UUID.randomUUID();
        SimpleProduct product = new SimpleProduct("iPhone", 130000);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));
        basketService.addProductInBasket(validId);
        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    void userBasketIsEmptyIfProductBasketIsEmpty() {
        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertThat(userBasket.getBasketItemList()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    void returnCorrectBasketWhenBasketHasItems() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(productId1, 2);
        basketMap.put(productId2, 1);
        Product product1 = new SimpleProduct("Product 1", 1000);
        Product product2 = new SimpleProduct("Product 2", 500);
        when(productBasket.getBasket()).thenReturn(basketMap);
        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));
        UserBasket userBasket = basketService.getUserBasket();
        assertThat(userBasket.getBasketItemList()).hasSize(2);
        assertThat(userBasket.getTotal()).isEqualTo(2500);
        List<BasketItem> items = userBasket.getBasketItemList();
        assertThat(items).anyMatch(item ->
                item.getProduct().equals(product1) && item.getQuantity() == 2);
        assertThat(items).anyMatch(item ->
                item.getProduct().equals(product2) && item.getQuantity() == 1);
    }
}
