package org.springframework.core.type;

import lombok.SneakyThrows;
import org.springframework.annotations.Scope;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface AnnotatedTypeMetaData {

    @SneakyThrows
    default MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName) {
        MultiValueMap<String, Object> str = new LinkedMultiValueMap<>();
        List<Object> objectList = new ArrayList<>();
        for (Annotation annotation : getClass().getAnnotations()) {
            Class<? extends Annotation> type = annotation.annotationType();
            for (Method method : type.getDeclaredMethods()) {
                Object value = method.invoke(annotation, (Object[]) null);
                objectList.add(value);
            }
        }
        str.add(annotationName.toLowerCase(), objectList);
        return str;
    }

    default boolean isAnnotated(String annotationName) {
        System.out.println("isAnnotated");
        for (Annotation annotation : getClass().getAnnotations()) {
            Class<? extends Annotation> type = annotation.annotationType();
            System.out.println(type.getSimpleName());
            if (type.getName().equals(annotationName)) {
                return true;
            }
        }
        return false;
    }
}
