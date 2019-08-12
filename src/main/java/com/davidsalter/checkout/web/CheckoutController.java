package com.davidsalter.checkout.web;

import org.springframework.web.bind.annotation.*;

/**
 * REST Controller responsible for scanning items.
 */
@RestController
public class CheckoutController {

    private final ShoppingCartService shoppingCartService;

    public CheckoutController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Scans an item and returns the total cost of the checkout.
     *
     * @param sku The sku of the item to scan
     * @return The total cost of the shopping in pence.
     */
    @PostMapping("/scan/items/{sku}")
    public int scan(@PathVariable String sku) {
        return shoppingCartService.scanItem(sku);
    }

    /**
     * Completes a checkout and removes all of the items that have been scanned.
     */
    @DeleteMapping("/scan/items")
    public void completeCheckout() {
        shoppingCartService.reset();
    }
}
