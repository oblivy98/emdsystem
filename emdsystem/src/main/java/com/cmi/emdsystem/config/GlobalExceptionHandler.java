package com.cmi.emdsystem.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.UNAUTHORIZED);

        logger.info("AuthenticationException: ", e);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(BadRequestException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST);

        logger.info("BadRequestException: ", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

}