package dev.jesus.project_spring_support_app.assist;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTOResponse;
import dev.jesus.project_spring_support_app.implementations.IGenericService;

@RestController
@RequestMapping(path = "${api-endpoint}/assists")
public class AssistController {

  private final IGenericService<AssistDTOResponse, AssistDTORequest> service;

  public AssistController(IGenericService<AssistDTOResponse, AssistDTORequest> service) {
    this.service = service;
  }

  @GetMapping("")
  public List<AssistDTOResponse> index() {
    return service.getEntities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AssistDTOResponse> SingleRequest(@PathVariable("id") Long id) {
    AssistDTOResponse assist = service.getEntityById(id);

    return ResponseEntity.ok().body(assist);
  }

  @PostMapping("")
  public ResponseEntity<AssistDTOResponse> storeEntity(@RequestBody AssistDTORequest dtoRequest) {

    if (dtoRequest.user_id() == null)
      return ResponseEntity.badRequest().build();

    if (dtoRequest.request_id() == null)
      return ResponseEntity.badRequest().build();

    AssistDTOResponse entityStored = service.storeEntity(dtoRequest);

    if (entityStored == null)
      return ResponseEntity.noContent().build();

    return ResponseEntity.status(201).body(entityStored);
  }

}
