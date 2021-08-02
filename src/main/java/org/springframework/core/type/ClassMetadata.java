package org.springframework.core.type;

public interface ClassMetadata {

    String getClassName();

    String[] getInterfaceNames();

    String[] getMemberClassNames();

    String getSuperClassName();

    default boolean hasSuperClass() {
        final Class<?> superclass = getClass().getSuperclass();
        return superclass != null;
    }

    boolean isAbstract();

    boolean isAnnotation();

    boolean isInterface();

    boolean isFinal();
}
