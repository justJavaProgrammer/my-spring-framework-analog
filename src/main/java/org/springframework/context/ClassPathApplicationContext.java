package org.springframework.context;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.beans.factory.finder.ImplementationFinder;
import org.springframework.beans.factory.support.XmlListableBeanFactory;

import javax.xml.parsers.ParserConfigurationException;

public class ClassPathApplicationContext extends AbstractApplicationContext {

    private String xmlFileName;

    public ClassPathApplicationContext(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        getBeanFactory();
    }

    public ConfigurableListableBeanFactory createBeanFactory() throws BeansException {
        Finder finder = new ImplementationFinder(xmlFileName);
        ConfigurableListableBeanFactory c = null;
        try {
            c = new XmlListableBeanFactory(xmlFileName, finder);
        } catch (ParserConfigurationException e) {
            throw new BeansException("Failed to load application context: " + e.getMessage(), e);
        }
        return c;
    }

    @SneakyThrows
    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return createBeanFactory();
    }
}
