package com.example.weather.controller;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Logger logger;

    @Autowired
    private String nameOfAuthor;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        logger.info("Undefined error occurred");
        return new ErrorResponse(nameOfAuthor, e.getLocalizedMessage(),
                e.getStackTrace(), LocalDateTime.now());
    }

}
