package com.davidsalter.checkout.domain;

/**
 * Class representing an item in a shopping basket and the number of times it has been purchased
 * in the current shopping basket.
 */
public class BasketItem {

    private String sku;
    private int numberPurchased;

    /**
     * Initialises and item specifying its sku and that it has been purchased 1 time.
     *
     * @param sku The sku
     */
    public BasketItem(String sku) {
        this.sku = sku;
        this.numberPurchased = 1;
    }

    /**
     * The sku for the product
     *
     * @return Sky
     */
    public String getSku() {
        return sku;
    }

    /**
     * The number of times the product has been purchased in the current shopping basked
     *
     * @return Number purchased
     */
    public int getNumberPurchased() {
        return numberPurchased;
    }

    /**
     * Increments the number of items of a specific SKU that have been purchased.
     */
    public void incrementPurchaseCount() {
        numberPurchased++;
    }
}
