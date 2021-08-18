package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanDefinition implements BeanDefinition {

    protected BeanDefinition definition;
    protected String className;
    protected String beanScope = "singleton";
    protected boolean isLazyInit = false;
    protected boolean isSingleton = true;
    protected boolean isPrototype = false;
    private String beanDefinitionName;


    public AbstractBeanDefinition() {

    }

    public AbstractBeanDefinition(BeanDefinition definition) {
        this.definition = definition;
    }

    @Override
    public String getScope() {
        if (this.definition != null)
            return this.definition.getScope();
        return this.beanScope;
    }

    @Override
    public void setScope(String scope) {
        if (this.definition != null)
            this.definition.setScope(scope);
        else {
            if (scope.equals("singleton"))
                this.isSingleton = true;
            else if (scope.equals("prototype")) {
                this.isSingleton = false;
                this.isPrototype = true;
            }
            this.beanScope = scope;
        }
    }


    @Override
    public boolean isLazyInit() {
        if (this.definition != null)
            return this.definition.isLazyInit();
        return this.isLazyInit;
    }

    @Override
    public boolean isSingleton() {
        if (this.definition != null)
            return this.definition.isSingleton();
        return isSingleton;
    }

    @Override
    public boolean isPrototype() {
        if (this.definition != null)
            return this.definition.isPrototype();
        return isPrototype;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        if (this.definition != null)
            this.definition.setLazyInit(lazyInit);
        else
            this.isLazyInit = lazyInit;
    }

    @Override
    public void setBeanClassName(String className) {
        if (this.definition != null)
            this.definition.setBeanClassName(className);
        else
            this.className = className;
    }

    @Override
    public String getBeanClassName() {
        if (this.definition != null)
            return this.definition.getBeanClassName();
        return this.className;
    }

    @Override
    public void setBeanDefinitionName(String beanDefinitionName) {
        if (this.definition != null)
            this.definition.setBeanDefinitionName(beanDefinitionName);
        this.beanDefinitionName = beanDefinitionName;
    }

    @Override
    public String getBeanDefinitionName() {
        if (this.definition != null)
            this.definition.getBeanDefinitionName();
        return this.beanDefinitionName;
    }

    public abstract AbstractBeanDefinition cloneBeanDefinition();
}
