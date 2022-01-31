package ru.clevertec.demo.service;

import ru.clevertec.demo.service.impl.ProductServiceImpl;
import ru.clevertec.demo.service.handler.ProductServiceHandler;

import java.lang.reflect.Proxy;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final ProductService productService = new ProductServiceImpl();

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public ProductService getProductService() {
        ClassLoader productServiceClassLoader = productService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = productService.getClass().getInterfaces();
        return (ProductService) Proxy.newProxyInstance(productServiceClassLoader,
                productServiceInterfaces, new ProductServiceHandler(productService));
    }

    private ServiceFactory() {
    }
}
