package tracker.expensetracker.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.expensetracker.Controller.Mapper.UserInfoMapper;
import tracker.expensetracker.Controller.dto.ChangePasswordRequest;
import tracker.expensetracker.Controller.dto.UserDetails;
import tracker.expensetracker.Controller.dto.UserInfo;
import tracker.expensetracker.service.User.UserService;
import tracker.expensetracker.service.User.UserServiceImpl;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    // get user info

    @GetMapping("/{userId}")
   public ResponseEntity<UserInfo> getUserInfo(
            @PathVariable String userId
    ) {
      var appUser = userServiceImpl.getUserInfo(userId);
      return ResponseEntity.status(HttpStatus.OK).body(
              UserInfoMapper.INSTANCE.mapToUserInfo(appUser)
      );
    }

    //change pass method

    @PostMapping("/{userId}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable String userId,
            @RequestBody ChangePasswordRequest changePasswordRequest // here as an input we will give both old and new pass as an input
    ) {
        userServiceImpl.changePassword(userId
                , changePasswordRequest.getOldPassword()
                , changePasswordRequest.getNewPassword());
        return ResponseEntity.ok().build();

    }

    //update user info
    @PostMapping ("/{userId}/")
    public ResponseEntity<UserInfo> updateUserDetails(
            @PathVariable String userId,
            @RequestBody UserDetails userDetails
    ) {

     var appUser = switch (userDetails.getUpdateRequestType()) {
            case UPDATE_EMAIL -> userServiceImpl.updateName(userId , userDetails.getEmail());
            case UPDATE_NAME -> userServiceImpl.updateName(userId , userDetails.getName());

        };

        userServiceImpl.updateName(userId , userDetails.getName());
        userServiceImpl.updateEmail(userId , userDetails.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        UserInfoMapper.INSTANCE.mapToUserInfo(appUser)
                );
    }
}
