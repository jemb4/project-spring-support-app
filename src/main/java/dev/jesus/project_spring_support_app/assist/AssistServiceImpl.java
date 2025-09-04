package dev.jesus.project_spring_support_app.assist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTOResponse;
import dev.jesus.project_spring_support_app.assist.mappers.AssistMapper;
import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.request.RequestServiceImpl;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserServiceImpl;

@Service
public class AssistServiceImpl implements IGenericService<AssistDTOResponse, AssistDTORequest> {

  private AssistRepository repository;
  private UserServiceImpl userService;
  private RequestServiceImpl requestService;

  public AssistServiceImpl(AssistRepository repository, UserServiceImpl userService,
      @Lazy RequestServiceImpl requestService) {
    this.repository = repository;
    this.userService = userService;
    this.requestService = requestService;
  }

  @Override
  public List<AssistDTOResponse> getEntities() {
    List<AssistDTOResponse> requests = new ArrayList<>();

    repository.findAll().forEach(c -> {
      AssistDTOResponse request = AssistMapper.toDTO(c);
      requests.add(request);
    });

    return requests;
  }

  @Override
  public AssistDTOResponse storeEntity(AssistDTORequest dtoRequest) {
    UserEntity user = userService.getUserEntityById(dtoRequest.user_id());
    RequestEntity request = requestService.getRequestEntityById(dtoRequest.request_id());

    AssistEntity assist = AssistMapper.toEntity(dtoRequest, user, request);
    AssistEntity assistStored = repository.save(assist);

    return AssistMapper.toDTO(assistStored);
  }

  @Override
  public AssistDTOResponse getEntityById(Long id) {
    AssistEntity assist = repository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Assist with id " + id + " not exist."));
    return AssistMapper.toDTO(assist);
  }

}
