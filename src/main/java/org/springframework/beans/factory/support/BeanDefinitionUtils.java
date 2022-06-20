package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;

public class BeanDefinitionUtils {


    /**
     *
     * @param definitions - map with bean definitions
     * @param c - bean class to search
     * @return bean definition, null otherwise
     */
    public static BeanDefinition getBeanDefinition(Map<String, BeanDefinition> definitions, Class<?> c)  {
        for (Map.Entry<String, BeanDefinition> entry : definitions.entrySet()) {
            BeanDefinition value = entry.getValue();
            if (value.getBeanClassName().equals(c.getName())) {
                return value;
            }
        }
        return null;
    }

}
