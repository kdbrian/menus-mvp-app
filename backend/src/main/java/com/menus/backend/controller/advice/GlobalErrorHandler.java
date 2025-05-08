package com.menus.backend.controller.advice;


import com.menus.backend.domain.dto.ErrorResponse;
import com.menus.backend.util.errors.EntityNotFoundError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalErrorHandler {


    @Value("${environment}")
    private String env;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .stack(env != null && env.equals("dev") ? e.getStackTrace()[0].toString() : null)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .stack(env != null && env.equals("dev") ? exception.getStackTrace()[0].toString() : null)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(EntityNotFoundError.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundError(EntityNotFoundError exception) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .stack(env != null && env.equals("dev") ? exception.getStackTrace()[0].toString() : null)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }


}
