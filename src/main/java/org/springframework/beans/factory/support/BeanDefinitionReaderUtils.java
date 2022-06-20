package org.springframework.beans.factory.support;

import lombok.SneakyThrows;

import java.util.Locale;

public class BeanDefinitionReaderUtils {

    @SneakyThrows
    public static AbstractBeanDefinition createBeanDefinition(String className,
                                                              String scope,
                                                              boolean lazyInit,
                                                              String beanName) {
        SimpleBeanDefinition definition = new SimpleBeanDefinition();
        definition.setBeanClassName(className);
        definition.setBeanDefinitionName(beanName);
        definition.setScope(scope); //TODO
        definition.setLazyInit(lazyInit);
        return definition;
    }

    public static String generateName(String className) {
        return className.substring(0,1).toLowerCase(Locale.ROOT) + className.substring(1);
    }
}
