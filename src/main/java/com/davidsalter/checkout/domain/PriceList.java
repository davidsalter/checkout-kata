package com.davidsalter.checkout.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a price list for a shop.  A price list consists of a set of
 * ItemPrice objects.  The public api allows items to be added to the price list and
 * the cost of a number of items to be calculated.
 *
 * @see ItemPrice
 */
public class PriceList {

    private Set<ItemPrice> priceList = new HashSet<>();

    /**
     * Adds an item (sku, price an discount price) to the price list
     *
     * @param itemPrice The item price object
     */
    public void addItem(ItemPrice itemPrice) {
        this.priceList.add(itemPrice);
    }

    /**
     * Calculates the cost of a specified item in pence using the pre-defined
     * discount rules.
     *
     * @param basketItem The items to calculate the cost for.
     * @return The cost in pence of the specified items
     */
    public int getCost(BasketItem basketItem) {
        for (ItemPrice ip : priceList) {
            if (ip.getSku().equalsIgnoreCase(basketItem.getSku())) {

                if (ip.getDiscountQuantity() == 0 || (basketItem.getNumberPurchased() < ip.getDiscountQuantity())) {
                    return ip.getUnitPrice() * basketItem.getNumberPurchased();
                } else {
                    int multipleDiscount = basketItem.getNumberPurchased() / ip.getDiscountQuantity();
                    if (multipleDiscount > 0) {
                        int price = multipleDiscount * ip.getDiscountPrice();
                        price += (basketItem.getNumberPurchased() % ip.getDiscountQuantity()) * ip.getUnitPrice();
                        return price;
                    }
                }
            }
        }

        return 0;
    }

    /**
     * Retrieves the number of entries in the price list
     *
     * @return Number of entries in price list
     */
    public int size() {
        return priceList.size();
    }
}
