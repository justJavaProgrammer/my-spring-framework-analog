package org.springframework;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.AnnotationConfigScanner;
import org.springframework.beans.factory.finder.ImplementationFinder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.type.classreading.DefaultAnnotationConfigDocumentReader;

public class SpringApplication {

    public static ConfigurableListableBeanFactory run(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        ImplementationFinder implementationFinder = new ImplementationFinder(packageName);
        AnnotationConfigScanner configScanner = new AnnotationConfigScanner(packageName);
        return new DefaultListableBeanFactory(implementationFinder, new DefaultAnnotationConfigDocumentReader("org"), configScanner);
    }
}
