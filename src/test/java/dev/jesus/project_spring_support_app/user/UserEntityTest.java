package dev.jesus.project_spring_support_app.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.jesus.project_spring_support_app.rol.RolEntity;

public class UserEntityTest {

  private UserEntity user;
  private RolEntity employee;

  @BeforeEach
  void setUp() {
    employee = new RolEntity(1L, "Employee");

    user = UserEntity.builder()
        .id(1L)
        .name("John")
        .surname("Doe")
        .rol(employee)
        .build();
  }

  @Test
  void testUserEntity_InitializationWithIdNameSurnameAndRol() {

    assertThat(user, is(instanceOf(UserEntity.class)));
    assertThat(user.getClass().getDeclaredFields().length, is(equalTo(6)));
    assertThat(user.getName(), is(equalTo("John")));
    assertThat(user.getSurname(), is(equalTo("Doe")));
    assertThat(user.getRol(), is(equalTo(employee)));
  }

  @Test
  void testRolEntity() {

    user.setId(2L);
    user.setName("Peter");
    user.setSurname("Mar");
    assertThat(user.getId(), is(equalTo(2L)));
    assertThat(user.getName(), is(equalTo("Peter")));
    assertThat(user.getSurname(), is(equalTo("Mar")));
  }
}
