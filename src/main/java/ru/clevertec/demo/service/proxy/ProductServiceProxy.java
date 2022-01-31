package ru.clevertec.demo.service.proxy;

import ru.clevertec.demo.annotation.Log;
import ru.clevertec.demo.entity.Product;
import ru.clevertec.demo.service.ProductService;
import ru.clevertec.demo.service.handler.ProductServiceHandler;
import ru.clevertec.demo.service.impl.ProductServiceImpl;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProductServiceProxy implements ProductService {
    private static ProductService productService;

    static {
        productService = new ProductServiceImpl();
        ClassLoader productServiceClassLoader = productService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = productService.getClass().getInterfaces();
        productService = (ProductService) Proxy.newProxyInstance(productServiceClassLoader,
                productServiceInterfaces, new ProductServiceHandler(productService));
    }

    @Override
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
