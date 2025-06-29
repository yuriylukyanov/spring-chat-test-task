package com.example.postgres.demo.exceptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
