package com.example.demo.BLL.exceptions;

public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
    }

    public InvalidTaskException(String message) {
        super(message);
    }

    public InvalidTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}