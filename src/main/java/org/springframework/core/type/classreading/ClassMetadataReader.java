package org.springframework.core.type.classreading;

public class ClassMetadataReader {

    private String className;
    private Class<?> aClass;

    public ClassMetadataReader(String className) {
        this.className = className;
    }

    public ClassMetadataReader(Class<?> aClass) {
        this.aClass = aClass;
    }



}
