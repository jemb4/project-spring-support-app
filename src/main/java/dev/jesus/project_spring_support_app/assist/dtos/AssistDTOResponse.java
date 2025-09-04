package dev.jesus.project_spring_support_app.assist.dtos;

import java.time.LocalDate;

public record AssistDTOResponse(Long id, String user, String request, String topic, LocalDate assisDate) {

}
