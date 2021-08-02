package org.springframework.core.type;

public class MethodMetaData implements AnnotationMetaData {


    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public String[] getInterfaceNames() {
        return new String[0];
    }

    @Override
    public String[] getMemberClassNames() {
        return new String[0];
    }

    @Override
    public String getSuperClassName() {
        return null;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public boolean isAnnotation() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
