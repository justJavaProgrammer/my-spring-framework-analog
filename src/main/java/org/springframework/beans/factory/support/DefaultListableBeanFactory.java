package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.annotations.PostConstruct;
import org.springframework.beans.BeanCreationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.beans.factory.finder.Scanner;
import org.springframework.beans.factory.stereotype.Autowired;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.core.type.classreading.DocumentReader;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class DefaultListableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {
    protected final Scanner scanner;
    protected final Map<String, BeanDefinition> definitions;
    protected final Map<String, Object> objects;
    protected final List<BeanPostProcessor> beanPostProcessors;
    protected final Finder finder;
    protected final DocumentReader reader;

    public DefaultListableBeanFactory(Finder finder, DocumentReader reader, Scanner scanner) {
        this.finder = finder;
        this.reader = reader;
        this.objects = new HashMap<>();
        this.definitions = new HashMap<>();
        this.scanner = scanner;
        this.beanPostProcessors = new ArrayList<>();
        init();
    }

    @SneakyThrows
    private void init() {
        initBeanDefinition();
        Set<Class<?>> classes = scanner.scanClassesAnnotatedWith(Component.class);
        for (Class<?> c : classes) {
            if (BeanPostProcessor.class.isAssignableFrom(c)) {
                Object bpp = createObjectWithConstructor(c);
                this.beanPostProcessors.add((BeanPostProcessor) bpp);
            }
        }
        initBeanCreation();
    }


    @Override
    public boolean containsBean(String beanName) {
        return this.definitions.containsKey(beanName);
    }

    @SneakyThrows
    @Override
    public <T> T getBean(Class<T> requiredType) {
        BeanDefinition beanDefinition = BeanDefinitionUtils.getBeanDefinition(definitions, requiredType);
        if (beanDefinition == null) {
            return null;
        }
        String beanName = beanDefinition.getBeanDefinitionName();

        Object o = this.objects.get(beanName);
        return requiredType.cast(o);
    }

    @SneakyThrows
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        System.out.println(requiredType);
        Object o = this.objects.get(name);
        return requiredType.cast(o);
    }

    @SneakyThrows
    @Override
    public Object getBean(String name) {
        return this.objects.get(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.definitions.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.definitions.get(name).isPrototype();
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
        return this.definitions.containsKey(name);
    }

    @Override
    public <T extends Annotation> T findAnnotationOnBean(String beanName, Annotation annotation) {
        return null;
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.definitions.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        final Set<String> keys = this.definitions.keySet();
        String[] s = new String[keys.size()];
        return keys.toArray(s);
    }

    @SneakyThrows
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition definition) {
        this.definitions.put(beanName, definition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.definitions.remove(beanName);
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotation) {
        return new String[0];
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.definitions.get(beanName);
    }


    @Override
    @SneakyThrows
    public <T> T createBean(Class<T> requireType) throws BeansException, IllegalAccessException, InstantiationException {
        BeanDefinition beanDefinition = BeanDefinitionUtils.getBeanDefinition(this.definitions, requireType);
        if (beanDefinition == null) {
            throw new BeanCreationException(String.format("Cannot find bean definition for class: %s. Add bean to your configuration", requireType.getName()));
        }

        Class<?> t = requireType;
        if (t.isInterface()) {
            final T c = finder.find(requireType);
            t = (Class<?>) c;
        }
        Object bean = createObjectWithConstructor(t);
        String beanDefinitionName = beanDefinition.getBeanDefinitionName();
        for (BeanPostProcessor bpp : this.beanPostProcessors) {
            bpp.beforeInitialization(beanDefinitionName, bean, this);
        }
        Method postConstruct = ReflectionUtils.getMethodAnnotatedWith(bean, PostConstruct.class);
        if(postConstruct != null) {
            postConstruct.invoke(bean);
        }
        return requireType.cast(bean);
    }

    protected void initBeanDefinition() {
        this.reader.registerBeanDefinitions(this);
    }

    protected void initBeanCreation() throws BeanCreationException {
        for (Map.Entry<String, BeanDefinition> entry : this.definitions.entrySet()) {
            BeanDefinition definition = entry.getValue();
            try {
                Class<?> c = Class.forName(definition.getBeanClassName());
                Object bean = createBean(c);
                this.objects.put(definition.getBeanDefinitionName(), bean);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | BeansException e) {
                throw new BeanCreationException(String.format("Cannot find class with package: %s", definition.getBeanClassName()));
            }
        }
    }

    @SneakyThrows
    protected Object createObjectWithConstructor(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            if (constructor.isAnnotationPresent(Autowired.class) || constructor.getParameterCount() == 0) {
                Parameter[] parameters = constructor.getParameters();
                Class<?>[] params = new Class<?>[parameters.length];
                Object[] paramsValues = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Class<?> type = parameter.getType();
                    Object bean = this.getBean(type);
                    if(bean == null)
                        throw new BeanCreationException(String.format("Bean: %s required bean: %s that not found. Add bean to your context and re-run application",
                                c.getName(), type.getSimpleName()));
                    paramsValues[i] = bean;
                    params[i] = type;
                }
                return c.getDeclaredConstructor(params).newInstance(paramsValues);
            }
        }
        throw new BeansException("Constructor with @Autowired annotation not found. Add @Autowired to your constructor or create default constructor with no parameters to fix this error");
    }
}
