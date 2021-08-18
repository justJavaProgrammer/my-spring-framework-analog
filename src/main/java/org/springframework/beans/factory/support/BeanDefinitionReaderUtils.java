package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.BeanAlreadyExistException;
import org.springframework.beans.factory.config.BeanDefinition;

public class BeanDefinitionReaderUtils {

    @SneakyThrows
    public static AbstractBeanDefinition createBeanDefinition(String className,
                                                              String scope,
                                                              boolean lazyInit,
                                                              BeanDefinitionRegistry registry) {
        SimpleBeanDefinition definition = new SimpleBeanDefinition();
        definition.setBeanClassName(className);
        definition.setBeanDefinitionName(generateName(className));
        definition.setScope(scope); //TODO
        definition.setLazyInit(lazyInit);
        return definition;
    }

    public static String generateName(String className) {
        return className.substring(0,1) + className.substring(1);
    }
    public static String generateNameForBeanDefinition(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // TODO
        return "";
    }

    @SneakyThrows
    public static String generateNameForBeanDefinition(String className, BeanDefinitionRegistry registry) {
        String generatedName = className.substring(0, 1) + className.substring(1);
        if (!registry.containsBeanDefinition(generatedName))
            return generatedName;
        throw new BeanAlreadyExistException("Bean with name: " + generatedName + " is already exist");
    }
}
