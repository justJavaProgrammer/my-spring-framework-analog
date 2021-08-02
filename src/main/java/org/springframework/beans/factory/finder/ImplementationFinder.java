package org.springframework.beans.factory.finder;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;

import java.util.Set;

public class ImplementationFinder implements Finder {
private Reflections reflections;

    public ImplementationFinder(String pkgToScan) {
        this.reflections = new Reflections(pkgToScan);
    }

    /**
     * search in packages implementation @param t if implementations != 1 @throws BeansException
     * @param <T>
     * @return
     */
    @Override
    public <T> T find(Class<T> t) throws BeansException {
        final Set<Class<? extends T>> subTypesOf = reflections.getSubTypesOf(t);
        if(subTypesOf.size() != 1) {
            throw new BeansException("Required 1 implementation but found " + subTypesOf.size());
        }
        return (T) subTypesOf.iterator().next();
    }
}
