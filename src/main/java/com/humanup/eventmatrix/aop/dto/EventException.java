package com.humanup.eventmatrix.aop.dto;

public class EventException extends HttpException {
    public EventException(String message) {
        super(message);
    }

    public EventException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Event";
    }
}
