package org.springframework.beans.factory.support;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;

import static org.junit.jupiter.api.Assertions.*;

class GenericBeanDefinitionTest {
private GenericBeanDefinition def = new GenericBeanDefinition();
    @Test
    void cloneBeanDefinition() {
        final boolean lazyInit = def.isLazyInit();
        Assert.assertEquals(false, lazyInit);
    }

    @Test
    void testEquals() {
        final boolean equals = def.equals(new GenericBeanDefinition());

        Assert.assertEquals(false, equals);
    }
}