package dev.jesus.project_spring_support_app.user.builder;

import org.springframework.stereotype.Component;

import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

@Component
public class UserEntityBuilder implements IUserBuilder {

  private final UserEntity user;

  public UserEntityBuilder() {
    this.user = new UserEntity();
  }

  @Override
  public UserEntityBuilder id(Long id) {
    user.setId(id);
    return this;
  }

  @Override
  public UserEntityBuilder name(String name) {
    user.setName(name);
    return this;
  }

  @Override
  public UserEntityBuilder surname(String surname) {
    user.setSurname(surname);
    return this;
  }

  @Override
  public UserEntityBuilder rol(RolEntity rol) {
    user.setRol(rol);
    return this;
  }

  @Override
  public UserEntity build() {
    return this.user;
  }

}
