package org.springframework.core.type.parsers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

class BeanXmlParserTest {
    private BeanXmlParser xml = new BeanXmlParser("src/main/resources/applicationContext.xml");
    @Test
    void parse() {
        final MultiValueMap<String, String> parse = xml.parse("");
        final String id = parse.getFirst("id");
        final String aClass = parse.getFirst("class");
        final String scope = parse.getFirst("scope");
        Assert.assertEquals("id1", id);
        Assert.assertEquals("org.springframework.test.Cat", aClass);
        Assert.assertEquals("singleton", scope);
    }

}