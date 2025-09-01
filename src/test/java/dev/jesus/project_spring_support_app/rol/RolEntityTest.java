package dev.jesus.project_spring_support_app.rol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RolEntityTest {

  private RolEntity rol;

  @BeforeEach
  void setUp() {
    rol = new RolEntity(1L, "support");
  }

  @Test
  void testRolEntity_InitializationWithIdAndName() {

    assertThat(rol, is(instanceOf(RolEntity.class)));
    assertThat(rol.getClass().getDeclaredFields().length, is(equalTo(2)));
    assertThat(rol.getName(), is(equalTo("support")));
  }

  @Test
  void testRolEntity() {
    rol.setId(2L);
    rol.setName("employee");
    assertThat(rol.getId(), is(equalTo(2L)));
    assertThat(rol.getName(), is(equalTo("employee")));
  }
}
