package com.lab7.exception;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException(String msg) {
        super(msg);
    }
}
