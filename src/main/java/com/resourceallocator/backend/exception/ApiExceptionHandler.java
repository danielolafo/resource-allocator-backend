package com.resourceallocator.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFound(ResourceNotFoundException ex) {
        return Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "error", ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequest(BadRequestException ex) {
        return Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validation(MethodArgumentNotValidException ex) {
        return Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Validaci\u00f3n fallida",
                "fieldErrors", ex.getBindingResult().getFieldErrors().stream()
                        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                        .toList());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> generic(Exception ex) {
        return Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", ex.getMessage());
    }
}