package org.springframework.beans.factory.finder;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationConfigScanner implements Scanner {
    private final Reflections reflections;

    public AnnotationConfigScanner(String pkg) {
        this.reflections = new Reflections(pkg);
    }

    @Override
    public Set<Class<?>> scanClassesAnnotatedWith(Class<? extends Annotation> annotation) {
        return this.reflections.getTypesAnnotatedWith(annotation);
    }
}
