package org.skypro.skyshop.model.product;

import org.skypro.skyshop.exception.NoSuchProductException;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price) {
        super(name);
        this.price = price;
        if (price<=0){
            throw new NoSuchProductException();
        }
    }

    @Override
    public int getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return name + ": " + price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
