package com.nikozka.endpoints;

import com.nikozka.dtos.ErrorResponse;
import com.nikozka.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EndpointExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ValidationException ex) {
    return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
  }
}
