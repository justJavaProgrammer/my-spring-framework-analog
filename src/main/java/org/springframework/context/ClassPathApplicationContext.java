package org.springframework.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.beans.factory.finder.ImplementationFinder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.XmlListableBeanFactory;

public class ClassPathApplicationContext extends AbstractApplicationContext {

    private String pkgToScan;

    public ClassPathApplicationContext(String pkgToScan) {
        this.pkgToScan = pkgToScan;
    }

    public ConfigurableListableBeanFactory createBeanFactory() {
        Finder finder = new ImplementationFinder(pkgToScan);
        ConfigurableListableBeanFactory c = new XmlListableBeanFactory(pkgToScan, finder);

        return c;
    }
    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return createBeanFactory();
    }
}
