package org.springframework.beans.factory.config;


/**
 * WARNING: IF YOU WANT TO INJECT INTO BEAN POST PROCESSOR USING AUTOWIRED ANNOTATION - USE ONLY CONSTRUCTOR INJECTION
 */
public interface BeanPostProcessor {

    Object beforeInitialization(String beanName, Object bean, ConfigurableListableBeanFactory factory);

}
