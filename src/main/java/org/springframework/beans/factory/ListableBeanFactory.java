package org.springframework.beans.factory;

import java.lang.annotation.Annotation;

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String name);

    <T extends Annotation>  T findAnnotationOnBean(String beanName, Annotation annotation);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForAnnotation(Class<? extends Annotation>  annotation);
}
