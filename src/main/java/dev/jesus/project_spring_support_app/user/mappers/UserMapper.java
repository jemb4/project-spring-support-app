package dev.jesus.project_spring_support_app.user.mappers;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;
import dev.jesus.project_spring_support_app.user.dtos.UserDTOResponse;

@Component
public class UserMapper {

  public static UserEntity toEntity(UserDTORequest dtoRequest) {
    UserEntity user = new UserEntity();
    user.setId(dtoRequest.id_user());

    return user;
  }

  public static UserDTOResponse toDTO(UserEntity entity) {
    UserDTOResponse dtoResponse = new UserDTOResponse(entity.getId(), entity.getName(), entity.getSurname(),
        entity.getRol().getName());

    return dtoResponse;
  }

}
