package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory implements ConfigurableListableBeanFactory {
    protected Map<String, Object> objects;
    protected Finder finder;

    public DefaultListableBeanFactory(Finder finder) {
        this.finder = finder;
        this.objects = new HashMap<>();
    }

    public DefaultListableBeanFactory() {
        this.objects = new HashMap<>();
    }

    @Override
    public boolean containsBean(String beanName) {
        return objects.containsKey(beanName);
    }

    @SneakyThrows
    @Override
    public <T> T getBean(Class<T> requiredType) {
        final String simpleName = requiredType.getSimpleName();
        String beanName = getBeanName(simpleName);
        return getBean(simpleName, requiredType);
    }

    @SneakyThrows
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        final T bean = createBean(requiredType);
        this.objects.put(name, bean);
        return bean;
    }

    @SneakyThrows
    @Override
    public Object getBean(String name) {

        final Object o = objects.get(name);
        if (o != null)
            return o;
        throw new BeansException("No beans found with this name");
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public boolean containsLocalBean(String name) {
        return false;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return null;
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return false;
    }

    @Override
    public <T extends Annotation> T findAnnotationOnBean(String beanName, Annotation annotation) {
        return null;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotation) {
        return new String[0];
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    private String getBeanName(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }

    @SneakyThrows
    public <T> T createBean(Class<T> requireType) throws BeansException, IllegalAccessException, InstantiationException {
        Class<?> t = requireType;
        if (t.isInterface()) {
            final T c = finder.find(requireType);
            t = (Class<?>) c;
        }
        return (T) t.newInstance();
    }
}
