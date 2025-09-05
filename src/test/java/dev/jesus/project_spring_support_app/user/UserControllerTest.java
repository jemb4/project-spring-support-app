package dev.jesus.project_spring_support_app.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.user.dtos.UserDTORequest;
import dev.jesus.project_spring_support_app.user.dtos.UserDTOResponse;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private IGenericService<UserDTOResponse, UserDTORequest> userService;

  @Autowired
  ObjectMapper mapper;

  UserDTOResponse user1;
  UserDTOResponse user2;

  @BeforeEach
  void setUp() {
    user1 = new UserDTOResponse(1L, "User1", "Surname", "employee");
    user2 = new UserDTOResponse(2L, "User2", "Surname", "employee");
  }

  @Test
  void testIndex_ShouldReturnAllUsers() throws Exception {
    List<UserDTOResponse> users = List.of(user1, user2);
    String json = mapper.writeValueAsString(users);

    when(userService.getEntities()).thenReturn(users);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getStatus(), is(equalTo(200)));
    assertThat(response.getContentAsString(), is(equalTo(json)));

  }

  @Test
  void testSingleUser_ById_ShouldReturnUser() throws Exception {
    Long pathVariable = 1L;

    when(userService.getEntityById(pathVariable)).thenReturn(user1);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/users/{id}", pathVariable))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getContentAsString(), containsString(user1.name()));

  }

}
