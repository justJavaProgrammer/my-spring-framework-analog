package org.springframework.core.type.classreading;

import org.springframework.core.type.AnnotationMetaData;
import org.springframework.core.type.ClassMetadata;

public interface MetadataReader {

    ClassMetadata getClassMetadata();

    AnnotationMetaData getAnnotationMetadata();

}
