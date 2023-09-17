package com.in28minuts.rest.webservices.todo.exceptionhandling;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int status;
    private String[] messages;
    private long timestamp;
}
