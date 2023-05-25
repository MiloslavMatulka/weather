package com.example.weather.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String author;
    private String message;
    private StackTraceElement[] stackTrace;
    private LocalDateTime timestamp;

}
