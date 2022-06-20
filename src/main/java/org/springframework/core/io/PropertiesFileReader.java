package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface PropertiesFileReader {
    String DEFAULT_PROPERTY_FILE_PATH_VALUE = new StringBuilder(System.getProperty("user.dir"))
            .append(File.separator)
            .append("src")
            .append(File.separator)
            .append("main")
            .append(File.separator)
            .append("resources")
            .append(File.separator)
            .append("application.properties").toString();

    Map<String, String> readProperties(String path) throws IOException;


    void registerPropertiesInPropertySourceHolder(String path) throws IOException;
}
