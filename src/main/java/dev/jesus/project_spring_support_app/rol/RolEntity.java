package dev.jesus.project_spring_support_app.rol;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import dev.jesus.project_spring_support_app.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rols")
public class RolEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonManagedReference
  @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
  private List<UserEntity> users;

  public RolEntity() {
  }

  public RolEntity(Long id, String name) {
    this.id = id;
    this.name = name;
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

  public List<UserEntity> getUsers() {
    return users;
  }

  public void setUsers(List<UserEntity> users) {
    this.users = users;
  }

}
