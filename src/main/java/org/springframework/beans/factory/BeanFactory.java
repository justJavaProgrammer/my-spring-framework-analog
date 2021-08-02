package org.springframework.beans.factory;

public interface BeanFactory {


    boolean containsBean(String beanName);

    <T> T getBean(Class<T> requiredType);

    <T> T getBean(String name, Class<T> requiredType);

    Object getBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);
}
