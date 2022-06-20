package org.springframework.core.type.classreading;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.MultiValueMap;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractXmlFileDocumentReader implements DocumentReader {
    protected final String xmlFileName;
    protected DocumentBuilderFactory builderFactory;

    public AbstractXmlFileDocumentReader(String xmlFileName) throws ParserConfigurationException {
        this.xmlFileName = xmlFileName;
        this.builderFactory = DocumentBuilderFactory.newInstance();
        this.builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    }
}
