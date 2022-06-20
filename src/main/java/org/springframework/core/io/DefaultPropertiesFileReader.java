package org.springframework.core.io;

import org.springframework.beans.factory.stereotype.Autowired;
import org.springframework.beans.factory.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class DefaultPropertiesFileReader implements PropertiesFileReader {
    private final PropertySourceHolder propertySourceHolder;

    @Autowired
    public DefaultPropertiesFileReader(PropertySourceHolder propertySourceHolder) {
        this.propertySourceHolder = propertySourceHolder;
    }

    @Override
    public Map<String, String> readProperties(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        Properties properties = new Properties();
        properties.load(fileReader);
        Map<String, String> props = new HashMap<>();
        for (String name: properties.stringPropertyNames())
            props.put(name, properties.getProperty(name));
        return props;
    }

    @Override
    public void registerPropertiesInPropertySourceHolder(String path) throws IOException {
        Map<String, String> map = this.readProperties(path);
        this.propertySourceHolder.setProperties(map);
    }
}
