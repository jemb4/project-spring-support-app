package dev.jesus.project_spring_support_app.request.dtos;

import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

public record RequestDTORequest(String description, UserEntity user, TopicEntity topic) {
}
