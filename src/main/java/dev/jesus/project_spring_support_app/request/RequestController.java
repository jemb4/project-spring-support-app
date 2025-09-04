package dev.jesus.project_spring_support_app.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOUpdate;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
public class RequestController {

  private final IRequestService<RequestDTOResponse, RequestDTORequest> service;

  public RequestController(IRequestService<RequestDTOResponse, RequestDTORequest> service) {
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

  @PostMapping("")
  public ResponseEntity<RequestDTOResponse> storeEntity(@RequestBody RequestDTORequest dtoRequest) {

    if (dtoRequest.description().isBlank())
      return ResponseEntity.badRequest().build();

    if (dtoRequest.user_id() == null) {
      return ResponseEntity.badRequest().build();
    }

    if (dtoRequest.topic_id() == null) {
      return ResponseEntity.badRequest().build();
    }

    RequestDTOResponse entityStored = service.storeEntity(dtoRequest);

    if (entityStored == null)
      return ResponseEntity.noContent().build();

    return ResponseEntity.status(201).body(entityStored);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RequestDTOResponse> updateRequest(
      @PathVariable("id") Long id,
      @RequestBody RequestDTOUpdate dtoRequest) {

    RequestDTOResponse updatedRequest = service.updateEntity(id, dtoRequest);

    return ResponseEntity.ok(updatedRequest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id) {
    try {
      service.deleteEntity(id);
      return ResponseEntity.noContent().build();
    } catch (IllegalStateException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
