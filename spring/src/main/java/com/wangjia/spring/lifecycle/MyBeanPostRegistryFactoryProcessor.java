package com.wangjia.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : wj
 * @date : 2021/5/31 00:08
 */
@Slf4j
@Component
public class MyBeanPostRegistryFactoryProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanPostRegistryFactoryProcessor.postProcessBeanFactory invoked");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Sex.class).getBeanDefinition();
        System.out.println("registry.getBeanDefinitionCount:"+registry.getBeanDefinitionCount());
        registry.registerBeanDefinition("sex",beanDefinition);
        System.out.println("MyBeanPostRegistryFactoryProcessor postProcessBeanDefinitionRegistry invoked");
        System.out.println("registry.getBeanDefinitionCount:"+registry.getBeanDefinitionCount());
    }
}
