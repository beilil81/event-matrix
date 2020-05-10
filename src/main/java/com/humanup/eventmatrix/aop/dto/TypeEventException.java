package com.humanup.eventmatrix.aop.dto;

public class TypeEventException extends HttpException {
    public TypeEventException(String message) {
        super(message);
    }

    public TypeEventException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create TypeEvent";
    }
}
