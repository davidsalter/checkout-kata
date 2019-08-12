package com.davidsalter.checkout.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a Supermarket checkout.
 */
public class Checkout {

    /**
     * The set of items within the shopping scanned at the checkout.
     */
    private Set<BasketItem> shoppingBasket = new HashSet();

    /**
     * The price list (including discounts) used at the checkout.
     */
    private PriceList priceList = new PriceList();

    /**
     * Calculates the total cost (in pence) of all the items scanned at the checkout.
     *
     * @return Total cost in pence
     */
    public int getTotalCost() {
        int totalCost = 0;

        for (BasketItem basketItem : shoppingBasket) {
            totalCost += priceList.getCost(basketItem);
        }

        return totalCost;
    }

    /**
     * Scan an item at the checkout.
     *
     * @param sku The sku of the item being scanned.
     */
    public void scan(String sku) {

        if ((priceList == null) || (priceList.size() == 0)) {
            throw new PriceListNotConfiguredException();
        }
        boolean updated = false;

        for (BasketItem basketItem : shoppingBasket) {
            if (basketItem.getSku().equalsIgnoreCase(sku)) {
                basketItem.incrementPurchaseCount();
                updated = true;
                break;
            }
        }

        if (!updated) {
            BasketItem basketItem = new BasketItem(sku);
            shoppingBasket.add(basketItem);
        }
    }

    /**
     * Sets the checkout pricelist
     *
     * @param priceList The checkout pricelist
     */
    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    /**
     * Empty the basket.
     */
    public void reset() {
        shoppingBasket.clear();
        priceList = new PriceList();
    }
}
