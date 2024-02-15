package com.example.blogPostWebServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseHandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundHandler(NotFoundException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setData(LocalDateTime.now());
        return error;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exceptionHandler (Exception e){
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setData(LocalDateTime.now());
        return error;
    }
}
