package org.springframework.core.type.classreading;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface DocumentReader {

    void registerBeanDefinitions(BeanDefinitionRegistry registry);
}
