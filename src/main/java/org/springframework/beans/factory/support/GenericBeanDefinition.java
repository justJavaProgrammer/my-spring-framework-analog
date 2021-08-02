package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

public class GenericBeanDefinition extends AbstractBeanDefinition {

    public GenericBeanDefinition() {
    }

    public GenericBeanDefinition(BeanDefinition definition) {
        super(definition);
    }

    @Override
    public AbstractBeanDefinition cloneBeanDefinition() {
        AbstractBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setScope(definition.getScope());
        beanDefinition.setLazyInit(definition.isLazyInit());

        return beanDefinition;
    }

    @Override
    public boolean equals(Object obj) {
        BeanDefinition def = (BeanDefinition) obj;
        if (getClass() != obj.getClass()) return false;
        if (obj == null) return false;
        final boolean scopeEquals = this.definition.getScope().equals(def.getScope());
        final boolean lazyInitEquals = this.definition.isLazyInit() == def.isLazyInit();
        final boolean isSingleton = this.definition.isSingleton() == def.isSingleton();
        final boolean isPrototype = this.definition.isPrototype() == def.isPrototype();
        return scopeEquals && lazyInitEquals && isSingleton && isPrototype;
    }

    @Override
    public String toString() {
        return "GenericBeanDefinition{" +
                "definition=" + definition +
                "scope " + definition.getScope()
                + "singleton " + definition.isSingleton() +
                "prototype " + definition.isPrototype() +
                "lazy init " + definition.isLazyInit() +
                '}';
    }
}
