package org.springframework.beans.factory.finder;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface Scanner {
    Set<Class<?>> scanClassesAnnotatedWith(Class<? extends Annotation> annotation);
}
