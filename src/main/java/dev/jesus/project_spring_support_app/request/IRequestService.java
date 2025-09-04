package dev.jesus.project_spring_support_app.request;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOUpdate;

public interface IRequestService<T, S> extends IGenericService<T, S> {
  RequestDTOResponse updateEntity(Long id, RequestDTOUpdate dtoUpdate);

  void deleteEntity(Long id);
}
