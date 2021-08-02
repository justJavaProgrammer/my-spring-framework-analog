package org.springframework.core.type.parsers;

import org.springframework.util.MultiValueMap;

public interface Parser {
    MultiValueMap<String, String> parse(String data);
}
