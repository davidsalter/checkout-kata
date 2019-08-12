package com.davidsalter.checkout.web;

import com.davidsalter.checkout.domain.PriceListNotConfiguredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice responsible for handling PriceListNotConfiguredException's
 * which will cause a HTTP 403 to be returned to the client.
 */
@ControllerAdvice
public class PriceListNotConfiguredAdvice {

    /**
     * Handler method for the PriceListNotConfiguredException.  This causes a HTTP
     * 403 to be returned to the caller.
     *
     * @param e The exception that is thrown
     * @return The message of the exception.
     */
    @ResponseBody
    @ExceptionHandler(PriceListNotConfiguredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String priceListNotConfiguredHandler(PriceListNotConfiguredException e) {
        return e.getMessage();
    }
}
