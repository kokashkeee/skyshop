package org.skypro.skyshop.model.product;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price) {
        super(name);
        this.price = price;
        if (price<=0){
            throw new IllegalArgumentException("неверно указана цена товара");
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
