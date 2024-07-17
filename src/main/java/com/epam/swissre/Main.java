package com.epam.swissre;

import static com.epam.swissre.utils.Constants.EMPTY_ORDER_LIST_ERROR_MESSAGE;
import static com.epam.swissre.utils.Constants.ORDER_SEPARATOR;

import java.util.Arrays;

import com.epam.swissre.exception.EmptyOrderListException;
import com.epam.swissre.service.BonusProgramService;
import com.epam.swissre.service.PrintService;
import com.epam.swissre.service.ReceiptService;
import com.epam.swissre.service.product.BeverageService;
import com.epam.swissre.service.product.ProductService;
import com.epam.swissre.service.product.SnackService;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 || args[0] == null) {
            throw new EmptyOrderListException(EMPTY_ORDER_LIST_ERROR_MESSAGE);
        }

        var productService = new ProductService(new BeverageService(), new SnackService());
        var printService = new PrintService();
        var bonusProgramService = new BonusProgramService();

        var receiptService = new ReceiptService(productService, printService, bonusProgramService);

        String[] orders = args[0].split(ORDER_SEPARATOR);
        receiptService.createReceipt(Arrays.asList(orders));
    }
}
