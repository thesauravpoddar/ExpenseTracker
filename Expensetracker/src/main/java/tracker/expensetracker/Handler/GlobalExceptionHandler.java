package tracker.expensetracker.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tracker.expensetracker.Controller.dto.ErrorResponse;
import tracker.expensetracker.exception.BadCredentialsException;
import tracker.expensetracker.exception.InvalidTokenException;
import tracker.expensetracker.exception.UserAlreadyExistsException;
import tracker.expensetracker.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> HandleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(
                     ErrorResponse.builder()
                             .errorCode(e.getErrorCode())
                             .errorMessage(e.getMessage())
                             .build()
                );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleUserNotFoundException(
            UserNotFoundException e
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(e.getErrorCode())
                        .build()
        );
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> HandleBadCredentialsException(
            BadCredentialsException e
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(e.getErrorCode())
                        .build()
        );
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> HandleInvalidTokenException(
            InvalidTokenException e
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(e.getErrorCode())
                        .build()
        );
    }
}
