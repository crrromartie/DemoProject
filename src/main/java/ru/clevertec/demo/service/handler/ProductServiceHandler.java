package ru.clevertec.demo.service.handler;

import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.demo.annotation.Log;
import ru.clevertec.demo.service.ProductService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProductServiceHandler implements InvocationHandler {
    private final ProductService productService;

    private static final String EMPTY_STRING = "";

    private static final String GET_PRODUCTS = "getProducts";

    public ProductServiceHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger logger = LogManager.getLogger(method.getDeclaringClass().getName());
        Gson parser = new Gson();
        Object invoke = method.invoke(productService, args);
/*
        if (method.getName().equals(GET_PRODUCTS)) {
            String arguments = EMPTY_STRING;
            if (args != null) {
                arguments = parser.toJson(args);
            }
            String result = EMPTY_STRING;
            if (invoke != null) {
                result = parser.toJson(invoke);
            }
            logger.log(Level.DEBUG, "{} args={}", method.getName(), arguments);
            logger.log(Level.DEBUG, "{} result={}", method.getName(), result);
        }
*/

        if (method.getAnnotation(Log.class) != null
                || productService.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Log.class) != null) {

            String arguments = EMPTY_STRING;
            if (args != null) {
                arguments = parser.toJson(args);
            }
            String result = EMPTY_STRING;
            if (invoke != null) {
                result = parser.toJson(invoke);
            }
            logger.log(Level.DEBUG, "{} args={}", method.getName(), arguments);
            logger.log(Level.DEBUG, "{} result={}", method.getName(), result);
        }

        return invoke;
    }
}
