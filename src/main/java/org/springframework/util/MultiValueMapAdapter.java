package org.springframework.util;

import lombok.SneakyThrows;

import java.util.*;

public class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V> {

    protected Map<K, List<V>> targetMap;

    public MultiValueMapAdapter() {
        this.targetMap = new LinkedHashMap<>();
    }

    public MultiValueMapAdapter(int size) {
        this.targetMap = new LinkedHashMap<>(size);
    }

    public MultiValueMapAdapter(Map<K, List<V>> targetMap) {
        this.targetMap = targetMap;
    }

    @Override
    public void add(K key, V value) {
        final List<V> values = this.targetMap.computeIfAbsent(key, k ->
                new ArrayList<>(1));
        values.add(value);
    }

    @Override
    public void addAll(K key, List<? extends V> values) {
        this.targetMap.put(key, (List<V>) values);
    }
    @SneakyThrows
    @Override
    public V getFirst(K key) {
        final List<V> vs = this.targetMap.get(key);
        if (vs != null && !vs.isEmpty()) {
            return vs.get(0);
        }
        throw new NoSuchElementException("No elements with this key " + key);
    }

    @Override
    public int size() {
        return this.targetMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.targetMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.targetMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.targetMap.containsKey(value);
    }

    @Override
    public List<V> get(Object key) {
        final List<V> vs = targetMap.get(key);
        if (vs != null && !vs.isEmpty()) {
            return vs;
        }
        throw new NoSuchElementException("No elements with this key " + key);
    }

    @Override
    public List<V> put(K key, List<V> value) {
        return this.targetMap.put(key, value);
    }

    @Override
    public List<V> remove(Object key) {
        final List<V> remove = this.targetMap.remove(key);
        return remove;
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<V>> m) {
        this.targetMap.putAll(m);
    }

    @Override
    public void clear() {
        this.targetMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.targetMap.keySet();
    }

    @Override
    public Collection<List<V>> values() {
        return this.targetMap.values();
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
        return this.targetMap.entrySet();
    }

    @Override
    public String toString() {
        return "MultiValueMapAdapter{" +
                "targetMap =" + targetMap +
                "size= " + targetMap.size() +
                "values= " + targetMap.values() +
                '}';
    }
}
