package org.springframework.beans.factory.stereotype;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
