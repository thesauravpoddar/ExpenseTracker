package tracker.expensetracker.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    USER_ALREADY_EXISTS("E409" , "user already exists"),
    USER_NOT_FOUND("E404" , "user not found"),
    PASS_NOT_MATCH("E401" , "password does not match"),
    INVALID_EXCESS_TOKEN("T401" , "invalid token exception");
    public final String ErrorCode;
    public final String ErrorMessage;

    }
