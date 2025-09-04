package dev.jesus.project_spring_support_app.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.mappers.RequestMapper;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.topic.TopicRepository;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserServiceImpl;

@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest> {

  private RequestRepository requestRepository;
  private UserServiceImpl userService;
  private TopicRepository topicRepository;

  public RequestServiceImpl(RequestRepository requestRepository, UserServiceImpl userService,
      TopicRepository topicRepository) {
    this.requestRepository = requestRepository;
    this.userService = userService;
    this.topicRepository = topicRepository;
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
    UserEntity user = userService.getUserEntityById(dtoRequest.user_id());
    TopicEntity topic = topicRepository.findById(dtoRequest.topic_id())
        .orElseThrow(() -> new RequestExceptionNotFound("Topic with id " + dtoRequest.topic_id() + " not exist."));

    RequestEntity request = RequestMapper.toEntity(dtoRequest, user, topic);
    RequestEntity requestStored = requestRepository.save(request);

    return RequestMapper.toDTO(requestStored);
  }

  public RequestDTOResponse getEntityById(Long id) {
    RequestEntity request = requestRepository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));
    return RequestMapper.toDTO(request);
  }
}
