package dev.jesus.project_spring_support_app.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.mappers.RequestMapper;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserRepository;

@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest> {

  private RequestRepository requestRepository;
  private UserRepository userRepository;
  private RequestRepository topicRepository;

  public RequestServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  public List<RequestDTOResponse> getEntities() {
    List<RequestDTOResponse> requests = new ArrayList<>();

    requestRepository.findAllByOrderByDateAsc().forEach(c -> {
      RequestDTOResponse request = RequestMapper.toDTO(c);
      requests.add(request);
    });

    return requests;
  }

  @Override
  public RequestDTOResponse storeEntity(RequestDTORequest dtoRequest) {
    UserEntity user = 
    RequestEntity request = RequestMapper.toEntity(dtoRequest); // enviar user y topic
    RequestEntity requestStored = requestRepository.save(request);
    return RequestMapper.toDTO(requestStored);
  }

  public RequestDTOResponse getEntityById(Long id) {
    RequestEntity request = requestRepository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));
    return RequestMapper.toDTO(request);
  }
}
