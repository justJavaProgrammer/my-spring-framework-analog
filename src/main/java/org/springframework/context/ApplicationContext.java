package org.springframework.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.support.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

public interface ApplicationContext extends
        BeanFactory,
        HierarchicalBeanFactory,
        ConfigurableApplicationContext,
        ListableBeanFactory,
        BeanDefinitionRegistry,
        ConfigurableListableBeanFactory,
        ResourceLoader {
}
