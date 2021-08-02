package org.springframework.context.support;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public interface ConfigurableApplicationContext extends HierarchicalBeanFactory {

    ConfigurableListableBeanFactory getBeanFactory();
}
