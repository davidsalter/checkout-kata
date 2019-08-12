package com.davidsalter.checkout.web;

import com.davidsalter.checkout.domain.ItemPrice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller responsible for maintaining the price list for items.
 */
@RestController
public class PriceListController {

    private final ShoppingCartService shoppingCartService;

    public PriceListController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Adds the specified items into the pricelist
     *
     * @param itemPrices An individual price list item for a sku.
     */
    @PostMapping("/priceList")
    public void setPriceList(@RequestBody List<ItemPrice> itemPrices) {
        for (ItemPrice item : itemPrices) {
            shoppingCartService.addPriceListItem(item);
        }
    }

    /**
     * Resets the price list
     */
    @DeleteMapping("/priceList")
    public void deletePriceList() {
        shoppingCartService.resetPriceList();
    }
}
