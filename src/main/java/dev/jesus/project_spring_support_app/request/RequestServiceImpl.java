package dev.jesus.project_spring_support_app.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.assist.AssistServiceImpl;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOUpdate;
import dev.jesus.project_spring_support_app.request.mappers.RequestMapper;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.topic.TopicRepository;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserServiceImpl;

@Service
public class RequestServiceImpl implements IRequestService<RequestDTOResponse, RequestDTORequest> {

  private RequestRepository requestRepository;
  private UserServiceImpl userService;
  private TopicRepository topicRepository;
  private AssistServiceImpl assistService;

  public RequestServiceImpl(RequestRepository requestRepository, UserServiceImpl userService,
      TopicRepository topicRepository, @Lazy AssistServiceImpl assistService) {
    this.requestRepository = requestRepository;
    this.userService = userService;
    this.topicRepository = topicRepository;
    this.assistService = assistService;
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

  @Override
  public RequestDTOResponse getEntityById(Long id) {
    RequestEntity request = requestRepository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));
    return RequestMapper.toDTO(request);
  }

  public RequestEntity getRequestEntityById(Long id) {
    return requestRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Request with id " + id + " not found"));
  }

  @Override
  public RequestDTOResponse updateEntity(Long id, RequestDTOUpdate dtoRequest) {
    RequestEntity request = requestRepository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));

    request.setDescription(dtoRequest.description());
    request.setAssisted(true);
    requestRepository.save(request);

    AssistDTORequest assistDTO = new AssistDTORequest(dtoRequest.user_id(), request.getId());
    assistService.storeEntity(assistDTO);

    return RequestMapper.toDTO(request);
  }

  @Override
  public void deleteEntity(Long id) {
    RequestEntity request = requestRepository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));

    if (!Boolean.TRUE.equals(request.is_assisted())) {
      throw new IllegalStateException("Request with id " + id + " cannot be deleted because it is not assisted.");
    }

    requestRepository.delete(request);
  }

}
