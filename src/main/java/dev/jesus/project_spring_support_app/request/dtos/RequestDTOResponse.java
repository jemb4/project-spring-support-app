package dev.jesus.project_spring_support_app.request.dtos;

import java.time.LocalDate;

import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

public record RequestDTOResponse(Long id, String description, UserEntity user, TopicEntity topic, boolean is_assisted,
    LocalDate date) {
}
