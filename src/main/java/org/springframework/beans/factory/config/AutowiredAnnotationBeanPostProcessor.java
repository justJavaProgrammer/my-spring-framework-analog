package org.springframework.beans.factory.config;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.stereotype.Autowired;
import org.springframework.beans.factory.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Component
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Logger logger = LoggerFactory.getLogger(AutowiredAnnotationBeanPostProcessor.class);

    @Override
    @SneakyThrows
    public Object beforeInitialization(String beanName, Object bean, ConfigurableListableBeanFactory factory) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                if (Modifier.isStatic(field.getModifiers())) {
                    this.logger.warn("Static field injection not supported");
                    return bean;
                }
                Class<?> type = field.getType();
                Object beanToInject = factory.getBean(type);
                field.setAccessible(true);
                field.set(bean, beanToInject);
            }
        }
        return bean;
    }
}
