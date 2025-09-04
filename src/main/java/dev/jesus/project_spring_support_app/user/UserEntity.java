package dev.jesus.project_spring_support_app.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import dev.jesus.project_spring_support_app.assist.AssistEntity;
import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.user.builder.UserEntityBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<RequestEntity> requests;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<AssistEntity> assists;

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

  public List<RequestEntity> getRequests() {
    return requests;
  }

  public void setRequests(List<RequestEntity> requests) {
    this.requests = requests;
  }

  public List<AssistEntity> getAssists() {
    return assists;
  }

  public void setAssists(List<AssistEntity> assists) {
    this.assists = assists;
  }

}
