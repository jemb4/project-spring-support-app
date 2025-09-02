package dev.jesus.project_spring_support_app.rol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;

@ExtendWith(MockitoExtension.class)
public class RolServiceImplTest {

  @InjectMocks
  private RolServiceImpl rolService;

  @Mock
  private RolRepository repository;

  @BeforeEach
  void setUp() {
    rolService = new RolServiceImpl(repository);
  }

  @Test
  void testGetRols_ShouldReturnAllRolls() {

    List<RolEntity> rolsMock = List.of(
        new RolEntity(1L, "support"),
        new RolEntity(2L, "employee"));

    when(repository.findAll()).thenReturn(rolsMock);
    List<RolDTOResponse> rols = rolService.getEntities();

    assertThat(rols.size(), is(equalTo(2)));
    assertThat(rols.get(0).name(), is(equalTo("support")));
    assertThat(rols.get(1).name(), is(equalTo("employee")));

  }

  @Test
  void testStoreEntity() {
    RolDTORequest user = new RolDTORequest("User");

    when(repository.save(Mockito.any(RolEntity.class))).thenReturn(new RolEntity(1L, user.name()));
    RolDTOResponse storedEntity = rolService.storeEntity(user);

    assertThat(storedEntity.name(), is(equalTo("User")));
  }
}
