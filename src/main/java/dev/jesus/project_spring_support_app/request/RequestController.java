package dev.jesus.project_spring_support_app.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
public class RequestController {

  private final IGenericService<RequestDTOResponse, RequestDTORequest> service;

  public RequestController(IGenericService<RequestDTOResponse, RequestDTORequest> service) {
    this.service = service;
  }

  @GetMapping("")
  public List<RequestDTOResponse> index() {
    return service.getEntities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<RequestDTOResponse> SingleRequest(@PathVariable("id") Long id) {
    RequestDTOResponse request = service.getEntityById(id);

    return ResponseEntity.ok().body(request);
  }

}
