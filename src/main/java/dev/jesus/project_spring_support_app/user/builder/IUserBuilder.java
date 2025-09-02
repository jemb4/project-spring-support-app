package dev.jesus.project_spring_support_app.user.builder;

import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

public interface IUserBuilder {

  public UserEntityBuilder id(Long id);

  public UserEntityBuilder name(String name);

  public UserEntityBuilder surname(String surname);

  public UserEntityBuilder rol(RolEntity rol);

  public UserEntity build();
}
