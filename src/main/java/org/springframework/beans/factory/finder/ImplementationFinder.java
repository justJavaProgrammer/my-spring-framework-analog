package org.springframework.beans.factory.finder;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeansException;

import java.util.Set;

public class ImplementationFinder implements Finder {
private final Reflections reflections;

    public ImplementationFinder(String pkgToScan) {
        this.reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(pkgToScan))
                .addScanners(new SubTypesScanner(false)));
    }

    /**
     * search in packages implementation @param t if implementations count != 1 @throws BeansException
     * @param <T>
     * @return
     */
    @Override
    public <T> T find(Class<T> t) throws BeansException {
        final Set<Class<? extends T>> subTypesOf = reflections.getSubTypesOf(t);
        if(subTypesOf.size() != 1) {
            throw new BeansException("Required 1 implementation of class: " + t.getName() + " but found " + subTypesOf.size() + " : " + subTypesOf);
        }
        return (T) subTypesOf.iterator().next();
    }
}
