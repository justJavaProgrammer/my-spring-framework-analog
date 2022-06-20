package org.springframework.beans.factory.config;

import org.springframework.annotations.Value;
import org.springframework.beans.factory.stereotype.Autowired;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.core.io.PropertiesFileReader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class ValueAnnotationBeanPostProcessor implements BeanPostProcessor {
    Map<String, String> properties;

    @Autowired
    public ValueAnnotationBeanPostProcessor() {
        this.properties = this.read();
    }

    @Override
    public Object beforeInitialization(String beanName, Object bean, ConfigurableListableBeanFactory factory) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                String value = annotation.value();
                String property = this.properties.get(value);
                field.setAccessible(true);
                try {
                    field.set(bean, property);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    private Map<String, String> read() {
        try {
            FileReader fileReader = new FileReader(PropertiesFileReader.DEFAULT_PROPERTY_FILE_PATH_VALUE);
            Properties properties = new Properties();
            properties.load(fileReader);
            Map<String, String> props = new HashMap<>();
            for (String name: properties.stringPropertyNames())
                props.put(name, properties.getProperty(name));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
       throw new RuntimeException("Application context creation failed");
    }
}
