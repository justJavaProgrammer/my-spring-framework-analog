package org.springframework.beans.factory.config;

public interface BeanDefinition extends Cloneable {
    String getScope();

    void setScope(String scope);

    boolean isLazyInit();

    boolean isSingleton();

    boolean isPrototype();

    void setLazyInit(boolean lazyInit);


}
