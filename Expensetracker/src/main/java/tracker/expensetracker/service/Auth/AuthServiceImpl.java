package tracker.expensetracker.service.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tracker.expensetracker.Data.model.AppUser;
import tracker.expensetracker.Data.repo.AppUserRepo;
import tracker.expensetracker.Util.JwtUtils;
import tracker.expensetracker.constant.ErrorMessages;
import tracker.expensetracker.constant.LoggingConstants;
import tracker.expensetracker.exception.BadCredentialsException;
import tracker.expensetracker.exception.InvalidTokenException;
import tracker.expensetracker.exception.UserAlreadyExistsException;
import tracker.expensetracker.exception.UserNotFoundException;
import tracker.expensetracker.service.Auth.Models.LoginRequest;
import tracker.expensetracker.service.Auth.Models.SignUpRequest;

import static tracker.expensetracker.constant.ErrorMessages.USER_ALREADY_EXISTS;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public String signUp(SignUpRequest signUpRequest) {
        var methodName = "AuthServiceImpl:signUp";
        log.info(LoggingConstants.START_METHOD_LOG, methodName , signUpRequest);

        // check whether user already exists or not
       if(appUserRepo.existsByEmail(signUpRequest.getEmail())) {
           log.info(LoggingConstants.ERROR_METHOD_LOG, methodName ,
                   signUpRequest.getEmail() + "email already exists");
           throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.getErrorMessage() ,
                   USER_ALREADY_EXISTS.getErrorCode());
       }

       // create App User Model
        var appUser = AppUser.builder()
                        .name(signUpRequest.getName()).
                        email(signUpRequest.getEmail()).
                        password(passwordEncoder.encode(signUpRequest.getPassword())).
                        build();

        // saving the app user
        appUserRepo.save(appUser);

        // Generate access token
        var accessToken =  JwtUtils.generateExcessToken(signUpRequest.getEmail());

        log.info(LoggingConstants.END_METHOD_LOG, methodName);
        return accessToken;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        // first find user by email
        var appUser = appUserRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->{
                    return new UserNotFoundException(
                            ErrorMessages.USER_NOT_FOUND.getErrorCode(),
                            ErrorMessages.USER_NOT_FOUND.getErrorMessage());
                });

        // match the password
        if(!passwordEncoder.matches(loginRequest.getPassword() , appUser.getPassword())) {
            throw new BadCredentialsException(ErrorMessages.PASS_NOT_MATCH.ErrorCode
                    , ErrorMessages.PASS_NOT_MATCH.getErrorMessage());
        }
        // if pass matches then generate access token
        //return access token
        return JwtUtils.generateExcessToken(loginRequest.getEmail());
    }

    @Override
    public String verifyToken(String accessToken) {
       var username = JwtUtils.getUsernameFromToken(accessToken)
               .orElseThrow(() -> {
                   return new InvalidTokenException(
                           ErrorMessages.INVALID_EXCESS_TOKEN.ErrorCode
                           , ErrorMessages.INVALID_EXCESS_TOKEN.getErrorMessage());
               });
        var appUser = appUserRepo.findByEmail(username)
                .orElseThrow(() ->{
                    return new UserNotFoundException(
                            ErrorMessages.USER_NOT_FOUND.getErrorCode(),
                            ErrorMessages.USER_NOT_FOUND.getErrorMessage());
                });
        return appUser.getUserId();
    }
}
