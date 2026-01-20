package tracker.expensetracker.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
    private final String ErrorCode;
    public InvalidTokenException(String ErrorCode , String ErrorMessage) {
        super(ErrorMessage);
        this.ErrorCode = ErrorCode;
    }
}
