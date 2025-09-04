package dev.jesus.project_spring_support_app.user;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;

@Component
public class UserMapper {

  public static UserEntity toEntity(UserDTORequest dtoRequest) {
    UserEntity user = new UserEntity();

    return user;
  }
}
