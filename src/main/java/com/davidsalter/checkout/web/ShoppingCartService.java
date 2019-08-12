package com.davidsalter.checkout.web;

import com.davidsalter.checkout.domain.Checkout;
import com.davidsalter.checkout.domain.ItemPrice;
import com.davidsalter.checkout.domain.PriceList;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private Checkout checkout = new Checkout();
    private static PriceList priceList = new PriceList();

    public void addPriceListItem(ItemPrice item) {
        priceList.addItem(item);
        checkout.setPriceList(priceList);
    }

    public int scanItem(String sku) {
        checkout.scan(sku);

        return checkout.getTotalCost();
    }

    public void reset() {
        checkout.reset();
    }

    public void resetPriceList() {
        priceList = new PriceList();
        checkout.setPriceList(priceList);
    }
}
