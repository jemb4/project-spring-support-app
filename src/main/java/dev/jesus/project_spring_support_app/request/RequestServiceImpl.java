package dev.jesus.project_spring_support_app.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.mappers.RequestMapper;

@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest> {

  private RequestRepository repository;

  public RequestServiceImpl(RequestRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<RequestDTOResponse> getEntities() {
    List<RequestDTOResponse> requests = new ArrayList<>();

    repository.findAllByOrderByDateAsc().forEach(c -> {
      RequestDTOResponse request = RequestMapper.toDTO(c);
      requests.add(request);
    });

    return requests;
  }

  @Override
  public RequestDTOResponse storeEntity(RequestDTORequest dtoRequest) {
    RequestEntity request = RequestMapper.toEntity(dtoRequest);
    RequestEntity requestStored = repository.save(request);
    return RequestMapper.toDTO(requestStored);
  }

  public RequestDTOResponse getEntityById(long id) {
    RequestEntity request = repository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id" + id + "not found."));
    return RequestMapper.toDTO(request);
  }
}
