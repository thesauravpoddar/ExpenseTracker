package tracker.expensetracker.Controller.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tracker.expensetracker.Controller.dto.AuthRequest;
import tracker.expensetracker.service.Auth.Models.LoginRequest;
import tracker.expensetracker.service.Auth.Models.SignUpRequest;

@Mapper(componentModel = "spring")
public interface AuthRequestMapper {

    AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);
    SignUpRequest mapToSignUpRequest(AuthRequest authRequest);

    LoginRequest mapToLoginRequest(AuthRequest authRequest);
}
