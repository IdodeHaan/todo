package com.in28minuts.rest.webservices.todo.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            ResourceNotFoundException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class})
//    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
    public ResponseEntity<Map<String, List<String>>> handleException(Exception exception) {
        HttpStatus status;
        List<String> errors = new ArrayList<>();
        ExceptionResponse exceptionResponse;
        if (exception instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errors.add(exception.getMessage());
//        } else if (exception instanceof InputValidationException) {
//            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
            errors = ((MethodArgumentNotValidException) exception)
                    .getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
        } else if (exception instanceof HttpMessageNotReadableException) {
                status = HttpStatus.BAD_REQUEST;
                errors.add(exception.getMessage());
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errors.add(exception.getMessage());
        }
//        exceptionResponse = createExceptionResponse(status.value(), (String[]) messages.toArray());
//        return new ResponseEntity<>(exceptionResponse, status);
        return new ResponseEntity<>(getErrorsMap(errors),new HttpHeaders(), status);
    }

    private ExceptionResponse createExceptionResponse(int status, String[] messages) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(status);
        response.setMessages(messages);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
