package com.example.weather.exception;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Logger logger;

    @Autowired
    private String nameOfAuthor;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception e,
                                                         WebRequest request) {
        logger.info("Error occurred: " + e.getLocalizedMessage() + ", "
                + request.getDescription(false));
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                nameOfAuthor, e.getLocalizedMessage(),
                request.getDescription(false), e.getStackTrace());
        return new ResponseEntity<>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
