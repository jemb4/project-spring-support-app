package dev.jesus.project_spring_support_app.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.jesus.project_spring_support_app.assist.AssistEntity;
import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.rol.RolEntity;

public class UserEntityTest {

  private UserEntity user;
  private RolEntity employee;
  private AssistEntity assist;
  private RequestEntity request;

  @BeforeEach
  void setUp() {
    employee = new RolEntity(1L, "Employee");
    assist = new AssistEntity();
    request = new RequestEntity(1L, "test", false, user, null);

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
    user.setAssists(assist);
    user.setRequests(request);
    assertThat(user.getId(), is(equalTo(2L)));
    assertThat(user.getName(), is(equalTo("Peter")));
    assertThat(user.getName(), is(equalTo("Mar")));
  }
}
