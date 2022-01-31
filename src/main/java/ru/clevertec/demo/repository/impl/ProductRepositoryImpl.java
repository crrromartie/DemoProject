package ru.clevertec.demo.repository.impl;

import ru.clevertec.demo.entity.Product;
import ru.clevertec.demo.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final ProductRepositoryImpl PRODUCT_REPOSITORY = new ProductRepositoryImpl();

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Milk", BigDecimal.valueOf(5)));
        products.add(new Product("Juice", BigDecimal.valueOf(7)));
        products.add(new Product("Salt", BigDecimal.valueOf(1)));
        return products;
    }

    private ProductRepositoryImpl() {
    }

    public static ProductRepositoryImpl getProductRepository() {
        return PRODUCT_REPOSITORY;
    }
}
