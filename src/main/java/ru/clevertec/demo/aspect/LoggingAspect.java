package ru.clevertec.demo.aspect;

import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.demo.annotation.Log;

@Aspect
public class LoggingAspect {
    private static final String EMPTY_STRING = "";

//    @Pointcut("execution(* ru.clevertec.demo.service.impl.ProductServiceImpl.getProducts())")
//    private void methodToBeLogged() {
//    }

    @AfterReturning(pointcut = "@annotation(ru.clevertec.demo.annotation.Log)", returning = "o")
    public void log(JoinPoint joinPoint, Object o) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
        Level loggerLevel = Level.getLevel(annotation.value().toString());
        Logger logger = LogManager.getLogger(joinPoint.getTarget());
        Gson parser = new Gson();
        String args = EMPTY_STRING;
        if (joinPoint.getArgs() != null) {
            args = parser.toJson(joinPoint.getArgs());
        }
        String result = EMPTY_STRING;
        if (o != null) {
            result = parser.toJson(o);
        }
        logger.log(loggerLevel, "{} args={}", methodSignature.getName(), args);
        logger.log(loggerLevel, "{} result={}", methodSignature.getName(), result);
    }
}
