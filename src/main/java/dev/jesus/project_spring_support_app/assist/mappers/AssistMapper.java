package dev.jesus.project_spring_support_app.assist.mappers;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.assist.AssistEntity;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.user.UserEntity;

@Component
public class AssistMapper {

  public static AssistEntity toEntity(AssistDTORequest dtoRequest, UserEntity user, RequestEntity request) {
    AssistEntity assist = new AssistEntity();
    assist.setUser(user);
    assist.setRequest(request);
    assist.setAssistDate(java.time.LocalDate.now());

    return assist;
  }

  public static AssistDTOResponse toDTO(AssistEntity assist) {
    AssistDTOResponse dtoResponse = new AssistDTOResponse(
        assist.getId(),
        assist.getUser().getName(),
        assist.getRequest().getDescription(),
        assist.getRequest().getTopic().getName(),
        assist.getAssistDate());

    return dtoResponse;
  }
}
