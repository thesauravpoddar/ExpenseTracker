package tracker.expensetracker.service.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tracker.expensetracker.Data.model.AppUser;
import tracker.expensetracker.Data.repo.AppUserRepo;
import tracker.expensetracker.constant.ErrorMessages;
import tracker.expensetracker.exception.BadCredentialsException;
import tracker.expensetracker.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser getUserInfo(String userId) {
        return appUserRepo.findById(userId)
                .orElseThrow(() ->{
                    return new UserNotFoundException(
                            ErrorMessages.USER_NOT_FOUND.getErrorCode(),
                            ErrorMessages.USER_NOT_FOUND.getErrorMessage());
                });
    }

    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {
        var appUser = appUserRepo.findById(userId)
                .orElseThrow(
                        () -> {
                            return new UserNotFoundException(
                                    ErrorMessages.USER_NOT_FOUND.getErrorCode(),
                                    ErrorMessages.USER_NOT_FOUND.getErrorMessage()
                            );
                        }
                );
        // check old password
        if(!passwordEncoder.matches(oldPassword , appUser.getPassword())) {
            throw new BadCredentialsException(
                    ErrorMessages.PASS_NOT_MATCH.getErrorCode(),
                    ErrorMessages.PASS_NOT_MATCH.getErrorMessage()
            );
        }
        // set new password

        appUser.setPassword(passwordEncoder.encode(newPassword));
        // save user

        appUserRepo.save(appUser);
    }

    @Override
    public AppUser updateName(String userId, String name) {
        //first finding the user
        var appUser = appUserRepo.findById(userId)
                .orElseThrow(() ->
                {
                    return new UserNotFoundException(
                            ErrorMessages.USER_NOT_FOUND.ErrorCode,
                            ErrorMessages.USER_NOT_FOUND.ErrorMessage
                    );
                });
        // update the name
        appUser.setName(name);
        //save the user
        return appUserRepo.save(appUser);
    }

    @Override
    public AppUser updateEmail(String userId, String email) {
        // first finding the user
        var appUser = appUserRepo.findById(userId)
                .orElseThrow(
                        () -> {
                            return new UserNotFoundException(
                                    ErrorMessages.USER_NOT_FOUND.getErrorCode(),
                                    ErrorMessages.USER_NOT_FOUND.getErrorMessage()
                            );
                        }
                );
        // update the email
        appUser.setEmail(email);
        //save the user
        return appUserRepo.save(appUser);
    }
}
