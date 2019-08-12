package com.davidsalter.checkout.domain;

/**
 * Runtime exception that is thrown when an attempt is made to scan items
 * but no price list has been configured.
 */
public class PriceListNotConfiguredException extends RuntimeException {

    public PriceListNotConfiguredException() {
        super("The price list has not been configured so no items can be scanned.");
    }
}
