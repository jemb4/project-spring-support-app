package dev.jesus.project_spring_support_app.request.dtos;

import java.time.LocalDate;

public record RequestDTOResponse(Long id, String description, String user, String topic, boolean is_assisted,
        LocalDate date) {
}
