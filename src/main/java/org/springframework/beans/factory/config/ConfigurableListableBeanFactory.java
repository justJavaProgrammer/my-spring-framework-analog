package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface ConfigurableListableBeanFactory extends AutowireCapableBeanFactory, BeanFactory, HierarchicalBeanFactory, ListableBeanFactory, BeanDefinitionRegistry {


    BeanDefinition getBeanDefinition(String beanName);
}
