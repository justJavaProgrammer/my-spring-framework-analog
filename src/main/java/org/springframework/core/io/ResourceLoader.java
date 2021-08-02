package org.springframework.core.io;

public interface ResourceLoader {

    String CLASS_PATH_PREFIX = "src.main.resources";

    ClassLoader getClassLoader();

    Resource getResources(String location);
}
