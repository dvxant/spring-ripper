package com.volnitsky.springripper.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

/**
 * Created by volnitsky on 24.04.17.
 */
public class ProfilingHandlerAfterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        Class beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("Start " + method);
                long start = System.nanoTime();
                System.out.println("______________");
                Object value = method.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println("_______________");
                System.out.println(end-start);
                System.out.println("End " + method);
                return value;
            });
        }
            return bean;
    }
}
