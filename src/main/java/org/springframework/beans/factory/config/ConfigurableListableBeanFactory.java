package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;

public interface  ConfigurableListableBeanFactory extends  AutowireCapableBeanFactory, BeanFactory, HierarchicalBeanFactory, ListableBeanFactory {


    BeanDefinition getBeanDefinition(String beanName);
}
