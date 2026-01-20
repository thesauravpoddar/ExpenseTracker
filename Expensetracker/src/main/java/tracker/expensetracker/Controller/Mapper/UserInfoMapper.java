package tracker.expensetracker.Controller.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tracker.expensetracker.Controller.dto.AuthRequest;
import tracker.expensetracker.Controller.dto.UserInfo;
import tracker.expensetracker.Data.model.AppUser;
import tracker.expensetracker.service.Auth.Models.LoginRequest;
import tracker.expensetracker.service.Auth.Models.SignUpRequest;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
    UserInfo mapToUserInfo(AppUser appUser);
}
