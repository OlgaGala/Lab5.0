package com.olga.exception;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException(String msg) {
        super(msg);
    }
}
