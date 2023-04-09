package com.example.usersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

/**
 * The ApiExceptionHandler class is a global exception handler for handling different types of exceptions
 * that may occur in the API. It uses the @ControllerAdvice annotation to handle exceptions globally
 * across all controllers in the application.
 */
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handles InvalidUserException and returns a ResponseEntity with a BAD_REQUEST status and the exception message.
     *
     * @param ex The InvalidUserException that occurred.
     * @return A ResponseEntity with the exception message and a BAD_REQUEST status.
     * @see InvalidUserException
     */
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<String> invalidUserException(InvalidUserException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles DuplicateUsernameException and returns a ResponseEntity with a CONFLICT status and the exception message.
     *
     * @param ex The DuplicateUsernameException that occurred.
     * @return A ResponseEntity with the exception message and a CONFLICT status.
     * @see DuplicateUsernameException
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> duplicateUsernameException(DuplicateUsernameException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Handles ResourceNotFoundException and returns a ResponseEntity with a NOT_FOUND status and the exception message.
     *
     * @param ex The ResourceNotFoundException that occurred.
     * @return A ResponseEntity with the exception message and a NOT_FOUND status.
     * @see ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with a BAD_REQUEST status and
     * the validation error messages concatenated into a single string.
     *
     * @param ex The MethodArgumentNotValidException that occurred.
     * @return A ResponseEntity with the validation error messages and a BAD_REQUEST status.
     * @see MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
        StringBuilder errorsBuilder = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            errorsBuilder.append(error.getDefaultMessage());
            errorsBuilder.append("\n");
        }
        return new ResponseEntity<>(errorsBuilder.toString(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles DateTimeParseException and returns a ResponseEntity with a BAD_REQUEST status and a generic error message.
     *
     * @param ex The DateTimeParseException that occurred.
     * @return A ResponseEntity with a generic error message and a BAD_REQUEST status.
     * @see DateTimeParseException
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> dateTimeParseException(DateTimeParseException ex) {
        return new ResponseEntity<>("invalid date format!", HttpStatus.BAD_REQUEST);
    }
}