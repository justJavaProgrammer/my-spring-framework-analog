package org.springframework.core.type.parsers;

import lombok.SneakyThrows;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class BeanXmlParser implements Parser {
    private String xmlFileName;
    private DocumentBuilderFactory builderFactory;

    public BeanXmlParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        init();
    }

    @SneakyThrows
    private void init() {
        this.builderFactory = DocumentBuilderFactory.newInstance();
        this.builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    }
    @SneakyThrows

    @Override
    public MultiValueMap<String, String> parse(String tagName) {
        DocumentBuilder db = builderFactory.newDocumentBuilder();
        Document doc = db.parse(new File(this.xmlFileName));
        doc.getDocumentElement().normalize();
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");
        final NodeList bean = doc.getElementsByTagName("bean");
        for (int i = 0; i < bean.getLength(); i++) {
            final Node node = bean.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                final String id = element.getAttribute("id");
                final String fullPath = element.getAttribute("class");
                final String scope = element.getAttribute("scope");
                map.add("id", id);
                map.add("class", fullPath);
                map.add("scope", scope);
            }
        }
        return map;
    }
}
