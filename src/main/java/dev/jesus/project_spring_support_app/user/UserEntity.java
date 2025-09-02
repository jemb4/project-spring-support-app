package dev.jesus.project_spring_support_app.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.user.builder.UserEntityBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  String name;
  String surname;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "rol_id")
  private RolEntity rol;

  public UserEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public RolEntity getRol() {
    return rol;
  }

  public void setRol(RolEntity rol) {
    this.rol = rol;
  }

  public static UserEntityBuilder builder() {
    return new UserEntityBuilder();
  }
}
