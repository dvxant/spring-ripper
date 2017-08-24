package com.volnitsky.springripper.BeanPostProcessor;

import com.volnitsky.springripper.BeanPostProcessor.InjectRandomInt;
import org.springframework.util.ReflectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by volnitsky on 24.04.17.
 */
public class InjectRandomIntAnnotationBeforeBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
       Field[] fields = bean.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if(annotation != null){
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int repeat = min + random.nextInt(max-min);
                field.setAccessible(true);
                ReflectionUtils.setField(field,bean,repeat);
            }
        });

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
