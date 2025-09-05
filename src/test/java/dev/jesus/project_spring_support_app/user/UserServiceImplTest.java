package dev.jesus.project_spring_support_app.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.jesus.project_spring_support_app.rol.RolEntity;
import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;
import dev.jesus.project_spring_support_app.user.dtos.UserDTOResponse;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  private UserEntity user1;
  private UserEntity user2;
  private RolEntity employee;

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository repository;

  @BeforeEach
  void setUp() {
    userService = new UserServiceImpl(repository);

    employee = new RolEntity(1L, "Employee");

    user1 = UserEntity.builder()
        .id(1L)
        .name("John")
        .surname("Doe")
        .rol(employee)
        .build();

    user2 = UserEntity.builder()
        .id(2L)
        .name("Peter")
        .surname("Doe")
        .rol(employee)
        .build();
  }

  @Test
  void testGetUsers_ShouldReturnAllUsers() {

    List<UserEntity> usersMock = List.of(
        user1,
        user2);

    when(repository.findAll()).thenReturn(usersMock);
    List<UserDTOResponse> users = userService.getEntities();

    assertThat(users.size(), is(equalTo(2)));
    assertThat(users.get(0).name(), is(equalTo("John")));
    assertThat(users.get(1).name(), is(equalTo("Peter")));

  }

  @Test
  void testStoreEntity() {
    UserDTORequest user = new UserDTORequest(4L);
    UserEntity testUser = UserEntity.builder()
        .id(4L)
        .name("test")
        .rol(employee)
        .build();

    when(repository.save(Mockito.any(UserEntity.class))).thenReturn(testUser);
    UserDTOResponse storedEntity = userService.storeEntity(user);

    assertThat(storedEntity.name(), is(equalTo("test")));
  }

}