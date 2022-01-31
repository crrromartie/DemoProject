package ru.clevertec.demo.service.impl;

import ru.clevertec.demo.annotation.Log;
import ru.clevertec.demo.annotation.LoggingLevel;
import ru.clevertec.demo.entity.Product;
import ru.clevertec.demo.repository.ProductRepository;
import ru.clevertec.demo.repository.impl.ProductRepositoryImpl;
import ru.clevertec.demo.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = ProductRepositoryImpl.getProductRepository();

    @Log(LoggingLevel.ERROR)
    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }
}
