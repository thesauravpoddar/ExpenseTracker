package tracker.expensetracker.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tracker.expensetracker.Controller.Mapper.AuthRequestMapper;
import tracker.expensetracker.Controller.dto.VerifyTokenRequest;
import tracker.expensetracker.Controller.dto.VerifyTokenResponse;
import tracker.expensetracker.constant.LoggingConstants;
import tracker.expensetracker.Controller.dto.AuthRequest;
import tracker.expensetracker.Controller.dto.AuthResponse;
import tracker.expensetracker.service.Auth.AuthService;
import tracker.expensetracker.service.Auth.AuthServiceImpl;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;
    //sign up
@PostMapping("/sign-up")
   public ResponseEntity<AuthResponse> signUp(
           @RequestBody AuthRequest authRequest) {
       var methodName = "RestController:signUp";
        log.info(LoggingConstants.START_METHOD_LOG, methodName , authRequest);
       var accessToken = authServiceImpl.signUp(
                AuthRequestMapper.INSTANCE.mapToSignUpRequest(authRequest)
        );
        log.info(LoggingConstants.END_METHOD_LOG, methodName);
       return ResponseEntity.status(HttpStatus.CREATED).body(
               AuthResponse.builder().accessToken(accessToken)
                       .build()
       );
   }
    //login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest authRequest
    ) {
    var methodName = "RestController:login";
        log.info(LoggingConstants.START_METHOD_LOG, methodName , authRequest);
        var accessToken = authServiceImpl.login(
                AuthRequestMapper.INSTANCE.mapToLoginRequest(authRequest)
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                AuthResponse.builder().build()
        );

    }

    // verify token
    @PostMapping("/verify-token")
    public ResponseEntity<VerifyTokenResponse> verifyToken(
            @RequestBody VerifyTokenRequest verifyTokenRequest
    ) {
        var methodName = "RestController:login";
        log.info(LoggingConstants.START_METHOD_LOG, methodName , verifyTokenRequest);
        var userId = authServiceImpl.verifyToken(verifyTokenRequest.getAccessToken());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                VerifyTokenResponse.builder().build()
        );

    }
}
