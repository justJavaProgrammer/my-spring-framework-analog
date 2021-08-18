package org.springframework.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.beans.factory.finder.ImplementationFinder;
import org.springframework.beans.factory.support.XmlListableBeanFactory;

public class ClassPathApplicationContext extends AbstractApplicationContext {

    private String xmlFileName;

    public ClassPathApplicationContext(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        getBeanFactory();
    }

    public ConfigurableListableBeanFactory createBeanFactory() {
        Finder finder = new ImplementationFinder(xmlFileName);
        ConfigurableListableBeanFactory c = new XmlListableBeanFactory(xmlFileName, finder);
        return c;
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return createBeanFactory();
    }
}
