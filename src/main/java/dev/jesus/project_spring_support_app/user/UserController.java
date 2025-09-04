package dev.jesus.project_spring_support_app.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;
import dev.jesus.project_spring_support_app.user.dtos.UserDTOResponse;

@RestController
@RequestMapping(path = "${api-endpoint}/rols")
public class UserController {

  private final IGenericService<UserDTOResponse, UserDTORequest> service;

  public UserController(IGenericService<UserDTOResponse, UserDTORequest> service) {
    this.service = service;
  }

  @GetMapping("")
  public List<UserDTOResponse> Index() {
    return service.getEntities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTOResponse> SingleUser(@PathVariable("id") long id) {
    UserDTOResponse user = service.getEntityById(id);

    return ResponseEntity.ok(user);
  }

}
