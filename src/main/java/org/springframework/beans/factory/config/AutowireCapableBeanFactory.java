package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

public interface AutowireCapableBeanFactory {

    <T> T createBean(Class<T> beanClass) throws BeansException, IllegalAccessException, InstantiationException;
}
