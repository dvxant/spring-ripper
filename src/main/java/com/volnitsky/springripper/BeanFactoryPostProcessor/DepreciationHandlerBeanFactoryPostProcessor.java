package com.volnitsky.springripper.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * Created by volnitsky on 24.04.17.
 */
public class DepreciationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beans = beanFactory.getBeanDefinitionNames();
        Arrays.stream(beans).forEach(name->{
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            try{
                Class beanClass = Class.forName(beanClassName);
                DeprecatedClass annotation = (DeprecatedClass) beanClass.getAnnotation(DeprecatedClass.class);
                if(annotation != null){
                    beanDefinition.setBeanClassName(annotation.newImpl().getName());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    }
