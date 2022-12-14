package com.intuit.riskchecker.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorData {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorData(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorData(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = List.of(error);
    }
}
