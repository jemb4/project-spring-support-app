package dev.jesus.project_spring_support_app.rol;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "${api-endpoint}/rols")
public class RolController {

  private final IGenericService<RolDTOResponse, RolDTORequest> service;

  public RolController(IGenericService<RolDTOResponse, RolDTORequest> service) {
    this.service = service;
  }

  @GetMapping("")
  public List<RolDTOResponse> Index() {
    return service.getEntities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<RolDTOResponse> SingleRol(@PathVariable("id") long id) {
    RolDTOResponse rol = service.getEntityById(id);
    if (rol == null)
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(rol);
  }

}
