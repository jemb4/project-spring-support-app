package dev.jesus.project_spring_support_app.request.mappers;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

@Component
public class RequestMapper {

  public static RequestEntity toEntity(RequestDTORequest dtoRequest, UserEntity user, TopicEntity topic) {
    RequestEntity request = new RequestEntity();
    request.setDescription(dtoRequest.description());
    request.setUser(user);
    request.setTopic(topic);
    request.setAssisted(false);
    request.setRequest_date(java.time.LocalDate.now());

    return request;
  }

  public static RequestDTOResponse toDTO(RequestEntity request) {
    RequestDTOResponse dtoResponse = new RequestDTOResponse(
        request.getId(),
        request.getDescription(),
        request.getUser().getName(),
        request.getTopic().getName(),
        request.is_assisted(),
        request.getRequest_date());

    return dtoResponse;
  }
}
