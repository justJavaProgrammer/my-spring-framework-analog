package org.springframework.beans.factory;

public interface HierarchicalBeanFactory extends BeanFactory, ListableBeanFactory {

    boolean containsLocalBean(String name);

    BeanFactory getParentBeanFactory();
}
