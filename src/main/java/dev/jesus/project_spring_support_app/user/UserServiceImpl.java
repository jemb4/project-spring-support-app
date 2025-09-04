package dev.jesus.project_spring_support_app.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;
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

  }

  public T storeEntity(S dto);

  public T getEntityById(Long id);

}
