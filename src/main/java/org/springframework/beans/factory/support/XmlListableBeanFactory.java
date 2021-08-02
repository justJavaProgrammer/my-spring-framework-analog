package org.springframework.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.finder.Finder;
import org.springframework.core.type.parsers.BeanXmlParser;
import org.springframework.core.type.parsers.Parser;
import org.springframework.util.MultiValueMap;

public class XmlListableBeanFactory extends DefaultListableBeanFactory {

    private final Parser  parser;

    public XmlListableBeanFactory(String s, Finder finder) {
        super(finder);
        this.parser = new BeanXmlParser(s);
    }
    @SneakyThrows
    @Override
    public <T> T createBean(Class<T> requireType) throws BeansException, IllegalAccessException, InstantiationException {
        final MultiValueMap<String, String> parse = parser.parse("");
        T impl;
        Class<?> c = Class.forName(parse.getFirst("class"));
        if(c.isInterface()) {
            final T o = (T) finder.find(c);
            return (T) o.getClass().newInstance();
        }

        return (T) c.newInstance();
    }
}
