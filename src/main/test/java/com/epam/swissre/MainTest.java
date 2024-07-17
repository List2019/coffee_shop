package com.epam.swissre;

import static com.epam.swissre.TestConstants.EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN;
import static com.epam.swissre.utils.Constants.EMPTY_ORDER_LIST_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.epam.swissre.exception.EmptyOrderListException;

class MainTest {

    @Test
    void shouldProcessOrderWithoutException() {
        try {
            String[] orders = new String[1];
            orders[0] = "large coffee with extra milk, small coffee with special roast, bacon roll, orange juice";
            Main.main(orders);
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }

    @Test
    void throwsExceptionWhenNoArguments() {
        EmptyOrderListException exception = assertThrows(EmptyOrderListException.class, () -> Main.main(new String[1]));
        assertEquals(EMPTY_ORDER_LIST_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void throwsExceptionWhenFirstArgumentIsNull() {
        EmptyOrderListException exception = assertThrows(EmptyOrderListException.class, () -> Main.main(new String[0]));
        assertEquals(EMPTY_ORDER_LIST_ERROR_MESSAGE, exception.getMessage());
    }
}
