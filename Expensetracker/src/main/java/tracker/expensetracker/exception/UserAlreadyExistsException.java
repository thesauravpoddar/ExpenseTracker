package tracker.expensetracker.exception;

import lombok.Getter;
import tracker.expensetracker.constant.ErrorMessages;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final String ErrorCode;
    public UserAlreadyExistsException(String ErrorCode , String ErrorMessage) {
        super(ErrorMessage);
        this.ErrorCode = ErrorCode;
    }
}
