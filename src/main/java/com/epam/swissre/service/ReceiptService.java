package com.epam.swissre.service;

import java.util.List;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.service.product.ProductService;

public class ReceiptService {

    private final ProductService productService;
    private final PrintService printService;
    private final BonusProgramService bonusProgramService;

    public ReceiptService(ProductService productService, PrintService printService, BonusProgramService bonusProgramService) {
        this.productService = productService;
        this.printService = printService;
        this.bonusProgramService = bonusProgramService;
    }

    public void createReceipt(List<String> orderList) {
        List<AbstractProduct> products = productService.getProducts(orderList);
        List<AbstractProduct> productsWithBonusPrograms = bonusProgramService.applyBonusPrograms(products);
        printService.printReceipt(productsWithBonusPrograms);
    }
}
