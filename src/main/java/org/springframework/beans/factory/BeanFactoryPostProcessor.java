package org.springframework.beans.factory;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory factory);
}
