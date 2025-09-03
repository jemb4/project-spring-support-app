package dev.jesus.project_spring_support_app.globals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
  // Request not found
  @ExceptionHandler(RequestExceptionNotFound.class)
  public ResponseEntity<ErrorResponse> handleRequestNotFound(RequestExceptionNotFound ex) {
    ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  // Request generic exception
  @ExceptionHandler(RequestException.class)
  public ResponseEntity<ErrorResponse> handleRequestException(RequestException ex) {
    ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  // Any other exception
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
