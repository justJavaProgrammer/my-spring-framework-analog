package org.springframework.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.Resource;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractApplicationContext implements ApplicationContext {
    protected Map<String, BeanDefinition> beanDefinitionMap;

    public AbstractApplicationContext() {
        this.beanDefinitionMap = new HashMap<>();
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.getBeanFactory().containsBean(beanName);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return this.getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return this.getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public Object getBean(String name) {
        return this.getBeanFactory().getBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.getBeanFactory().isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) {
        return this.getBeanFactory().isPrototype(name);
    }

    @Override
    public boolean containsLocalBean(String name) {
        return this.getBeanFactory().containsLocalBean(name);
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return this.getBeanFactory().getParentBeanFactory();
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.getBeanFactory().containsLocalBean(name);
    }

    @Override
    public <T extends Annotation> T findAnnotationOnBean(String beanName, Annotation annotation) {
        return this.getBeanFactory().findAnnotationOnBean(beanName, annotation);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.getBeanFactory().getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotation) {
        return this.getBeanFactory().getBeanNamesForAnnotation(annotation);
    }

    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    @Override
    public Resource getResources(String location) {
        return null;
    }
}
