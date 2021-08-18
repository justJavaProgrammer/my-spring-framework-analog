package org.springframework.core.type.classreading;

import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultDocumentReader {
    private String xmlFileName;
    private DocumentBuilderFactory builderFactory;

    public DefaultDocumentReader(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        init();
    }

    @SneakyThrows
    private void init() {
        this.builderFactory = DocumentBuilderFactory.newInstance();
        this.builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    }

    @SneakyThrows
    public ConcurrentHashMap<String, BeanDefinition> getCreatedBeanDefinitions(BeanDefinitionRegistry registry) {

        MultiValueMap<String, String> map = getBeans();
        // TODO make creation bean(s) from xml
        ConcurrentHashMap<String, BeanDefinition> definitions = new ConcurrentHashMap<>(64);
        final List<String> id = map.get("id");
        final List<String> classes = map.get("class");
        final List<String> scopes = map.get("scope");
        for (int i = 0; i < classes.size(); i++) {
            definitions.put(id.get(i), BeanDefinitionReaderUtils.createBeanDefinition(classes.get(i),
                    scopes.get(i),
                    false,
                    registry)); // TODO creation id name
        }
        return definitions;
    }


    @SneakyThrows
    public MultiValueMap getBeans() {
        DocumentBuilder db = builderFactory.newDocumentBuilder();
        Document doc = db.parse(new File(this.xmlFileName));
        doc.getDocumentElement().normalize();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        final NodeList beans = doc.getElementsByTagName("beans");
        for (int i = 0; i < beans.getLength(); i++) {
            Node node = beans.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                final NodeList bean = element.getElementsByTagName("bean");
                for (int j = 0; j < bean.getLength(); j++) {
                    Node beanNode = bean.item(j);
                    Element beanElement = (Element) beanNode;
                    final String id = beanElement.getAttribute("id");
                    final String aClass = beanElement.getAttribute("class");
                    final String scope = beanElement.getAttribute("scope");
                    map.add("id", id);
                    map.add("class", aClass);
                    map.add("scope", scope != null ? scope : "singleton");
                }
            }
        }
        return map;
    }
}
