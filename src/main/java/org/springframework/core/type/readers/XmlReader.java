package org.springframework.core.type.readers;

import lombok.SneakyThrows;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlReader implements Reader {

    private String xmlFileName;
    private DocumentBuilderFactory builderFactory;

    public XmlReader(String xmlFileName) {
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
    public String read() {
        Document document = (Document) builderFactory.newDocumentBuilder();
        return document.toString();
    }
}
