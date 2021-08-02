package org.springframework.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultiValueMapAdapterTest {
private MultiValueMapAdapter<Integer, String>stringMultiValueMapAdapter =
        new MultiValueMapAdapter<>();
    @Test
    void addTest() {
        stringMultiValueMapAdapter.add(1, "hello");
        Assert.assertEquals("hello", stringMultiValueMapAdapter.getFirst(1) );

    }

    @Test
    void containsKeyTest() {

        stringMultiValueMapAdapter.add(1, "hello");
        final boolean b = stringMultiValueMapAdapter.containsKey(1);
        Assert.assertEquals(true, b);
    }

    @Test
    void addAllTest() {
        stringMultiValueMapAdapter.addAll(2, Arrays.asList("hello", "hello2", "b", "c"));
        Assert.assertEquals( Arrays.asList("hello", "hello2", "b", "c"), this.stringMultiValueMapAdapter.get(2));
    }

    @Test
    void entrySetTest() {
        for (Map.Entry<Integer, List<String>> integerListEntry : this.stringMultiValueMapAdapter.entrySet()) {

        }
    }
}