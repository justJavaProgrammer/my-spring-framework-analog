package org.springframework.util;

import java.util.List;
import java.util.Map;

public interface MultiValueMap<K, V> extends Map<K, List<V>> {

    void add(K key, V value);

    void addAll(K key, List<? extends V> values);

    default void addIfAbsent(K key, V value) {
        if(!this.containsKey(key))
            this.add(key, value);
    }

    V getFirst(K key);

}
