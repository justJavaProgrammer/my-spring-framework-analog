package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.core.type.classreading.DefaultDocumentReader;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory implements ConfigurableListableBeanFactory {
    protected Map<String, BeanDefinition> definitions;
    protected Map<String, Object> objects;
    protected Finder finder;
    protected DefaultDocumentReader reader;

    public DefaultListableBeanFactory(Finder finder, DefaultDocumentReader reader) {
        this.finder = finder;
        this.reader = reader;
        init();
    }

    private void init() {
        this.definitions = createBeanDefinitions();
        for (Map.Entry<String, BeanDefinition> stringBeanDefinitionEntry : definitions.entrySet()) {
            this.objects = createObjectFromBeanDefinition(stringBeanDefinitionEntry.getValue());
        }
    }


    @Override
    public boolean containsBean(String beanName) {
        return this.definitions.containsKey(beanName);
    }

    @SneakyThrows
    @Override
    public <T> T getBean(Class<T> requiredType) {
        //todo
        return null;
    }

    @SneakyThrows
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        System.out.println(requiredType);
        return (T) this.objects.get(name);
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
        this.definitions.put(beanName, null);

        //  throw new BeanAlreadyExistException("Bean with name: " + beanName + " already exist");
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
        Class<?> t = requireType;
        if (t.isInterface()) {
            final T c = finder.find(requireType);
            t = (Class<?>) c;
        }
        return (T) t.newInstance();
    }

    @SneakyThrows
    public <T> ConcurrentHashMap<String, Object> createObjectFromBeanDefinition(BeanDefinition definition) {
        ConcurrentHashMap<String, Object> objects = new ConcurrentHashMap<>();
        String name = definition.getBeanClassName();
        if (definition.isSingleton() && !containsBeanDefinition(definition.getBeanDefinitionName())) {
            T c = (T) Class.forName(name).newInstance();
            objects.put(name, c);
        }
        return objects;
    }

    public ConcurrentHashMap<String, BeanDefinition> createBeanDefinitions() {
        ConcurrentHashMap<String, BeanDefinition> definitions = this.reader.getCreatedBeanDefinitions(this);
        return definitions;
    }
}
