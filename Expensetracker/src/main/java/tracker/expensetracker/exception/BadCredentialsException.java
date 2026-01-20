package tracker.expensetracker.exception;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException {
    private final String ErrorCode;
    public BadCredentialsException(String ErrorCode , String ErrorMessage) {
        super(ErrorMessage);
        this.ErrorCode = ErrorCode;
    }
}
