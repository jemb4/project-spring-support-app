package dev.jesus.project_spring_support_app.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
public class RequestController {

  private final IGenericService<RolDTOResponse, RolDTORequest> service;

  public RequestController(IGenericService<RolDTOResponse, RolDTORequest> service) {
    this.service = service;
  }

  @GetMapping("")
  public List<RolDTOResponse> index() {
    return service.getEntities();
  }

}
