package dev.jesus.project_spring_support_app.rol;

import java.util.ArrayList;
import java.util.List;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;
import dev.jesus.project_spring_support_app.rol.mappers.RolMapper;

public class RolServiceImpl implements IGenericService<RolDTOResponse, RolDTORequest> {

  private RolRepository repository;

  public RolServiceImpl(RolRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<RolDTOResponse> getEntities() {
    List<RolDTOResponse> rols = new ArrayList<>();

    repository.findAll().forEach(c -> {
      RolDTOResponse rol = RolMapper.toDTO(c);
      rols.add(rol);
    });

    return rols;
  }

  @Override
  public RolDTOResponse storeEntity(RolDTORequest rolDTORequest) {
    RolEntity rol = RolMapper.toEntity(rolDTORequest);
    RolEntity rolStored = repository.save(rol);
    return RolMapper.toDTO(rolStored);
  }

}
