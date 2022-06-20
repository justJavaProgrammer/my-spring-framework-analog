package org.springframework.core.type.classreading;

public abstract class AbstractAnnotationConfigDocumentReader implements DocumentReader {
    protected final String basePackages;

    protected AbstractAnnotationConfigDocumentReader(String basePackages) {
        this.basePackages = basePackages;
    }
}
