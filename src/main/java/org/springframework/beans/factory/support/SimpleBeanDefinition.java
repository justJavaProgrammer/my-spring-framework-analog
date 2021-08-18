package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;

public class SimpleBeanDefinition extends AbstractBeanDefinition {

    public SimpleBeanDefinition() {
        super();
    }

    public SimpleBeanDefinition(BeanDefinition definition) {
        super(definition);
    }

    @SneakyThrows
    @Override
    public AbstractBeanDefinition cloneBeanDefinition() {
        return (AbstractBeanDefinition) this.clone();
    }


    @Override
    public String toString() {
        return "SimpleBeanDefinition{" +
                "definition=" + definition +
                ", className='" + className + '\'' +
                ", beanScope='" + beanScope + '\'' +
                ", isLazyInit=" + isLazyInit +
                ", isSingleton=" + isSingleton +
                ", isPrototype=" + isPrototype +
                '}';
    }
}
