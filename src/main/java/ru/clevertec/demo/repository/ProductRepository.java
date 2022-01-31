package ru.clevertec.demo.repository;

import ru.clevertec.demo.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
}
