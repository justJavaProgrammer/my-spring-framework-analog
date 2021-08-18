package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.core.type.classreading.DefaultDocumentReader;


public class XmlListableBeanFactory extends DefaultListableBeanFactory {


    public XmlListableBeanFactory(String s, Finder finder) {
        super(finder, new DefaultDocumentReader(s));
    }

    @SneakyThrows
    @Override
    public <T> T createBean(Class<T> requireType) throws BeansException, IllegalAccessException, InstantiationException {
        Class<?> t = requireType;
        if (t.isInterface()) {
            final T c = finder.find(requireType);
            final T o = (T) c.getClass().newInstance();
        }
        return (T) t.newInstance();
    }
}
