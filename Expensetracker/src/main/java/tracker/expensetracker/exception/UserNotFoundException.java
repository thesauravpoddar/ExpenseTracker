package tracker.expensetracker.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String ErrorCode;
    public UserNotFoundException(String ErrorCode , String ErrorMessage) {
        super(ErrorMessage);
        this.ErrorCode = ErrorCode;
    }
}
