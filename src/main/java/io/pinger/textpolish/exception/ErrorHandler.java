package io.pinger.textpolish.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        final ErrorDetails error = new ErrorDetails(ErrorCode.INVALID_DATA, "Validation failed for one or more fields", Instant.now(), errors);
        return new ResponseEntity<>(error, exception.getStatusCode());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException exception) {
        final ErrorDetails errorDetails = new ErrorDetails(exception.getErrorCode(), exception.getMessage(), Instant.now());
        return new ResponseEntity<>(errorDetails, exception.getHttpStatus());
    }

}
