package com.rel.pcs.exception;

import com.rel.pcs.response.ErrorResponse;
import com.rel.pcs.response.ErrorResponse.ErrorResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
    String errorMessage =
        exception.getFieldError() != null ? exception.getFieldError().getDefaultMessage() : "";
    ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.BAD_REQUEST)
        .setMessage(errorMessage)
        .build();

    return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handle(MissingServletRequestParameterException exception) {
    String errorMessage = exception.getMessage();
    ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.BAD_REQUEST)
        .setMessage(errorMessage)
        .build();

    return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
  }

}
