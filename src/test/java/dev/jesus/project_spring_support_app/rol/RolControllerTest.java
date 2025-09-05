package dev.jesus.project_spring_support_app.rol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTORequest;
import dev.jesus.project_spring_support_app.rol.dtos.RolDTOResponse;

@WebMvcTest(controllers = RolController.class)
public class RolControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private IGenericService<RolDTOResponse, RolDTORequest> rolService;

  @Autowired
  ObjectMapper mapper;

  RolDTOResponse employee;
  RolDTOResponse support;

  @BeforeEach
  void setUp() {
    employee = new RolDTOResponse(1L, "Employee");
    support = new RolDTOResponse(1L, "Support");
  }

  @Test
  @DisplayName("Test get endpoint without id")
  void testIndex_ShouldReturnARols() throws Exception {
    List<RolDTOResponse> rols = List.of(employee, support);
    String json = mapper.writeValueAsString(rols);

    when(rolService.getEntities()).thenReturn(rols);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/rols"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getStatus(), is(equalTo(200)));
    assertThat(response.getContentAsString(), is(equalTo(json)));
  }

  @Test
  @DisplayName("Test single get endpoint by id")
  void testSingleRol_ById_ShouldReturnRol() throws Exception {
    Long pathVariable = 1L;

    when(rolService.getEntityById(pathVariable)).thenReturn((employee));
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/rols/{id}", pathVariable))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getContentAsString(), containsString(employee.name()));
  }
}
