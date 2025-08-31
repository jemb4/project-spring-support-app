package dev.jesus.project_spring_support_app.rol.mappers;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;

@Component
public class RolMapper {

  public static RolEntity toEntity(RolDTORequest dtoRequest) {
    RolEntity rol = new RolEntity();
    rol.setName(dtoRequest.name());

    return rol;
  }

  public static RolDTOResponse toDTO(RolEntity entity) {
    RolDTOResponse dtoResponse = new RolDTOResponse(entity.getId(), entity.getName());

    return dtoResponse;
  }
}
