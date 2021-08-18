package org.springframework.beans;

public class BeanAlreadyExistException extends BeansException {
    public BeanAlreadyExistException(String message) {
        super(message);
    }

    public BeanAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
