package com.davidsalter.checkout.domain;

/**
 * Class representing an item price.  Each instantiation has: a SKU, a unit price,
 * a discount price and a quantity to be purchased for the discount price to apply.
 */
public class ItemPrice {

    private String sku;
    private int unitPrice;
    private int discountQuantity;
    private int discountPrice;

    /**
     * Construct a new Item Price
     *
     * @param sku              The sku for the product
     * @param unitPrice        The price (in pence) for a single product
     * @param discountQuantity The number of items purchased to get the discountPrice
     * @param discountPrice    The discount price (in pence) when the discountQuantity number of items are purchased
     */
    public ItemPrice(String sku, int unitPrice, int discountQuantity, int discountPrice) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.discountQuantity = discountQuantity;
        this.discountPrice = discountPrice;
    }

    /**
     * The Stock Keeping Unit id (sku)
     *
     * @return Sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * The number of items to purchase to get the discountPrice
     *
     * @return Discount Quantity
     */
    public int getDiscountQuantity() {
        return discountQuantity;
    }

    /**
     * The discount price when the discount number of items are purchased
     *
     * @return Discount price
     */
    public int getDiscountPrice() {
        return discountPrice;
    }

    /**
     * The unit price when fewer than the discountQuantity number of items are purchased
     *
     * @return Unit price
     */
    public int getUnitPrice() {
        return unitPrice;
    }

}
