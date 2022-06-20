package org.springframework.core.type.classreading;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;

public class DefaultAnnotationConfigDocumentReader extends AbstractAnnotationConfigDocumentReader {
    private final Reflections reflections;

    public DefaultAnnotationConfigDocumentReader(String basePackages) {
        super(basePackages);
        ConfigurationBuilder b = new ConfigurationBuilder()
                .addUrls(ClasspathHelper.forPackage(basePackages))
                .addClassLoader(this.getClass().getClassLoader())
                .setScanners(new TypeElementsScanner(), new SubTypesScanner(false), new TypeAnnotationsScanner());
        this.reflections = new Reflections(b);
    }

    @Override
    public void registerBeanDefinitions(BeanDefinitionRegistry registry) {
        Set<Class<?>> classes = this.reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> c : classes) {
            Component annotation = c.getAnnotation(Component.class);
            String beanName = annotation.beanName();
            String scope = annotation.scope();
            if (beanName.equals("")) {
                beanName = BeanDefinitionReaderUtils.generateName(c.getSimpleName());
            }
            AbstractBeanDefinition beanDefinition = BeanDefinitionReaderUtils.createBeanDefinition(c.getName(), scope, false, beanName);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
