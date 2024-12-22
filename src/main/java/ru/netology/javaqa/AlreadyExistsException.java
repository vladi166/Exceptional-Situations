package ru.netology.javaqa;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String ss) {
        super(ss);
    }
}
