package org.springframework.core.io;

import org.springframework.beans.factory.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertySourceHolder {
    private Map<String, String> props = new HashMap<>();

    public void addProperty(String key, String value) {
        this.props.put(key, value);
    }

    public void removeProperty(String key) {
        this.props.remove(key);
    }

    public void setProperties(Map<String, String> props) {
        this.props = props;
    }

    public String getProperty(String key) {
        return props.get(key);
    }
}
