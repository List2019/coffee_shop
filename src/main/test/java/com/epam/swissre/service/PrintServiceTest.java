package com.epam.swissre.service;

import static com.epam.swissre.TestConstants.EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.TestUtils;
import com.epam.swissre.model.AbstractProduct;

class PrintServiceTest {

    private final PrintService printService = new PrintService();

    @Test
    void shouldPrintReceiptWithoutException() {
        try {
            List<AbstractProduct> products = TestUtils.getProducts();
            printService.printReceipt(products);
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }
}
