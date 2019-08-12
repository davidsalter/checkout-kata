package com.davidsalter.checkout.web;

import com.davidsalter.checkout.domain.ItemPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/scan/items");
    }

    @Test
    public void shouldCheckoutSingleItemAndReturnPrice() throws Exception {
        configurePriceList();

        ResponseEntity results = checkout("A");

        assertThat(results.getBody().toString(), is("50"));
        assertThat(results.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldCheckoutMultipleSingleItemsAndObtainDiscount() {
        configurePriceList();

        ResponseEntity results = checkout("A");
        results = checkout("A");
        results = checkout("A");

        assertThat(results.getBody().toString(), is("130"));
        assertThat(results.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldCheckoutMultipleDiscountAndSingleItemsAndObtainDiscount() {
        configurePriceList();

        ResponseEntity results = checkout("A");
        results = checkout("B");
        results = checkout("A");
        results = checkout("B");
        results = checkout("A");
        results = checkout("C");

        assertThat(results.getBody().toString(), is("195"));
        assertThat(results.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldReturnForbiddenIfNoPriceListConfigured(){
        this.restTemplate.delete("http://localhost:" + port + "/priceList");

        ResponseEntity results = checkout("A");

        assertThat(results.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    private ResponseEntity<String> checkout(String sku) {
        return restTemplate.exchange("http://localhost:" + port + "/scan/items/" + sku, HttpMethod.POST, null,
                String.class);
    }

    private void configurePriceList() {
        List<ItemPrice> ip = new ArrayList<>();
        ip.add(new ItemPrice("A", 50, 3, 130));
        ip.add(new ItemPrice("B", 30, 2, 45));
        ip.add(new ItemPrice("C", 20, 0, 0));

        this.restTemplate.postForObject("http://localhost:" + port + "/priceList", ip, ItemPrice.class);
    }
}
