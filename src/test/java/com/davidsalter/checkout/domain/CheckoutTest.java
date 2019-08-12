package com.davidsalter.checkout.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutTest {

    private Checkout checkout = new Checkout();
    private PriceList priceList = new PriceList();

    @Before
    public void setUp() throws Exception {

        priceList.addItem(new ItemPrice("A", 50, 3, 130));
        priceList.addItem(new ItemPrice("B", 30, 2, 45));
        priceList.addItem(new ItemPrice("C", 20, 0, 0));
        priceList.addItem(new ItemPrice("D", 15, 0, 0));

        checkout.setPriceList(priceList);
    }

    @Test(expected = PriceListNotConfiguredException.class)
    public void shouldReturnExceptionWhenScanWithNoPriceList() {
        checkout.setPriceList(null);
        checkout.scan("A");
    }

    @Test
    public void shouldCost0whenIBuyNothing() {
        assertThat(checkout.getTotalCost(), CoreMatchers.is(0));
    }

    @Test
    public void shouldCost50WhenIBuyA() {
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(50));
    }

    @Test
    public void shouldCost30WhenIBuyB() {
        checkout.scan("B");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(30));
    }

    @Test
    public void shouldCost20WhenIBuyC() {
        checkout.scan("C");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(20));
    }

    @Test
    public void shouldCost45WhenIBuyBB() {
        checkout.scan("B");
        checkout.scan("B");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(45));
    }
    @Test
    public void shouldCost100WhenIBuyAA() {
        checkout.scan("A");
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(100));
    }

    @Test
    public void shouldCost130WhenIBuyAAA() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(130));
    }

    @Test
    public void shouldCost160WhenIBuyAAAB() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(160));
    }

    @Test
    public void shouldCostMe210WhenIBuyAAABBCD() {
        checkout.scan("A");
        checkout.scan("C");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("D");
        checkout.scan("B");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(210));
    }

    @Test
    public void shouldCostMe260WhenIBuyAAAAAA() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(260));
    }

    @Test
    public void shouldCostMe260WhenIBuyAAAAAAA() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        assertThat(checkout.getTotalCost(), CoreMatchers.is(310));
    }
}
