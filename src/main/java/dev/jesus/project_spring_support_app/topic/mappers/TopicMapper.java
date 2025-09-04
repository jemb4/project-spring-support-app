package dev.jesus.project_spring_support_app.topic.mappers;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.topic.dtos.TopicDTORequest;
import dev.jesus.project_spring_support_app.topic.dtos.TopicDTOResponse;
import dev.jesus.project_spring_support_app.topic.TopicEntity;

@Component
public class TopicMapper {

  public static TopicEntity toEntity(TopicDTORequest dtotopic) {
    TopicEntity topic = new TopicEntity();
    topic.setName(dtotopic.name());
    topic.setDescription(dtotopic.description());

    return topic;
  }

  public static TopicDTOResponse toDTO(TopicEntity topic) {
    TopicDTOResponse dtoResponse = new TopicDTOResponse(
        topic.getId(),
        topic.getName(),
        topic.getDescription());

    return dtoResponse;
  }
}
