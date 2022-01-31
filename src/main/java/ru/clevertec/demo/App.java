package ru.clevertec.demo;

import ru.clevertec.demo.entity.Product;
import ru.clevertec.demo.repository.impl.ProductRepositoryImpl;
import ru.clevertec.demo.service.ProductService;
import ru.clevertec.demo.service.ServiceFactory;
import ru.clevertec.demo.service.impl.ProductServiceImpl;
import ru.clevertec.demo.service.proxy.ProductServiceProxy;

import java.util.List;

public class App {
    public static void main(String[] args) {
//        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
//        ProductService productService = new ProductServiceProxy();
        ProductService productService = new ProductServiceImpl();
        System.out.println(productService.getProducts());
    }
}
