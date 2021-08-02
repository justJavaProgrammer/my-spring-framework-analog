package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanDefinition implements BeanDefinition {
    protected BeanDefinition definition;

    public AbstractBeanDefinition() {

    }

    public AbstractBeanDefinition(BeanDefinition definition) {
        this.definition = definition;
    }

    @Override
    public String getScope() {
        return this.definition.getScope();
    }

    @Override
    public void setScope(String scope) {
        this.definition.setScope(scope);
    }

    @Override
    public boolean isLazyInit() {
        return this.definition.isLazyInit();
    }

    @Override
    public boolean isSingleton() {
        return this.definition.isSingleton();
    }

    @Override
    public boolean isPrototype() {
        return this.definition.isPrototype();
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.definition.setLazyInit(lazyInit);
    }

    public abstract AbstractBeanDefinition cloneBeanDefinition();
}
