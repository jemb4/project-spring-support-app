package dev.jesus.project_spring_support_app.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;
import dev.jesus.project_spring_support_app.user.dtos.UserDTOResponse;
import dev.jesus.project_spring_support_app.user.mappers.UserMapper;

@Service
public class UserServiceImpl implements IGenericService<UserDTOResponse, UserDTORequest> {

  private UserRepository repository;

  @Override
  public List<UserDTOResponse> getEntities() {
    List<UserDTOResponse> users = new ArrayList<>();

    repository.findAll().forEach(c -> {
      UserDTOResponse user = UserMapper.toDTO(c);
      users.add(user);
    });

    return users;
  }

  @Override
  public UserDTOResponse storeEntity(UserDTORequest userDTORequest) {
    UserEntity user = UserMapper.toEntity(userDTORequest);
    UserEntity userStored = repository.save(user);
    return UserMapper.toDTO(userStored);
  }

  @Override
  public UserDTOResponse getEntityById(Long id) {
    return repository.findById((long) id)
        .map(UserMapper::toDTO)
        .orElse(null);
  }

}
