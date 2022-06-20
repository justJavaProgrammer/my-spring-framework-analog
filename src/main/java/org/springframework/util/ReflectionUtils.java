package org.springframework.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static Method getMethodAnnotatedWith(Object bean, Class<? extends Annotation> annotation) {
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                return method;
            }
        }
        return null;
    }
}
