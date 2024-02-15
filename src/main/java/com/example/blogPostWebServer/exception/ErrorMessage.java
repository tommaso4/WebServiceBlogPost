package com.example.blogPostWebServer.exception;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ErrorMessage {
    private String message;
    private LocalDateTime data;
}
