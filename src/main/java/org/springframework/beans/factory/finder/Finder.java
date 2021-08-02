package org.springframework.beans.factory.finder;

import org.springframework.beans.BeansException;

public interface Finder {

    <T> T find(Class<T> t) throws BeansException;
}
