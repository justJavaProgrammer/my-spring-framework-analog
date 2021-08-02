package org.springframework.util;

import java.util.List;
import java.util.Map;

public class LinkedMultiValueMap<K, V> extends MultiValueMapAdapter<K, V>  {

    public LinkedMultiValueMap() {
    }

    public LinkedMultiValueMap(int size) {
        super(size);
    }

    public LinkedMultiValueMap(Map<K, List<V>> otherMap) {
        super(otherMap);
    }

    public LinkedMultiValueMap<K, V> deepCopy() {
        try {
            return (LinkedMultiValueMap<K, V>) this.clone();
        } catch (CloneNotSupportedException e) {
            LinkedMultiValueMap<K, V> clone = new LinkedMultiValueMap<>(this);
            return new LinkedMultiValueMap<>(clone);
        }
    }

}
