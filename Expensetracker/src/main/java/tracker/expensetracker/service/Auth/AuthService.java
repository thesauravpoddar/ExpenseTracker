package tracker.expensetracker.service.Auth;

import org.springframework.stereotype.Component;
import tracker.expensetracker.service.Auth.Models.LoginRequest;
import tracker.expensetracker.service.Auth.Models.SignUpRequest;


public interface AuthService {

    public String signUp(SignUpRequest signUpRequest);

    String login(LoginRequest loginRequest);

    String verifyToken(String accessToken);
}
