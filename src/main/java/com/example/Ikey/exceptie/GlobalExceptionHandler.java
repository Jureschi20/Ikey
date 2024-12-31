package com.example.Ikey.exceptie;
import com.example.Ikey.exceptie.UserAlreadyExistsException;
import com.example.Ikey.exceptie.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice // Indică faptul că această clasă va trata excepțiile global
public class GlobalExceptionHandler {

  // Prinde excepția UserAlreadyExistsException
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // Răspuns cu mesaj și cod 400
  }

  // Prinde excepția UserNotFoundException
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // Răspuns cu mesaj și cod 404
  }

  // Prinde orice altă excepție
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
  }
}
