package dev.jesus.project_spring_support_app.rol;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rols")
public class RolEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public RolEntity(Long id, String name) {
    this.id = id;
    this.name = name;
  }

}
