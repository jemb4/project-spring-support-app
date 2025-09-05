package dev.jesus.project_spring_support_app.assist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTOResponse;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;

@WebMvcTest(controllers = AssistController.class)
public class AssistControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private IGenericService<AssistDTOResponse, AssistDTORequest> assistService;

  @Autowired
  ObjectMapper mapper;

  AssistDTOResponse assist1;
  AssistDTOResponse assist2;

  @BeforeEach
  void setUp() {
    LocalDate assisDate = LocalDate.now();
    assist1 = new AssistDTOResponse(1L, "Peter", "request", "topic", assisDate);
    assist2 = new AssistDTOResponse(2L, "John", "request", "topic", assisDate);
  }

  @Test
  void testIndex_ShouldReturnAllAssists() throws Exception {
    List<AssistDTOResponse> assists = List.of(assist1, assist2);
    String json = mapper.writeValueAsString(assists);

    when(assistService.getEntities()).thenReturn(assists);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/assists"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getStatus(), is(equalTo(200)));
    assertThat(response.getContentAsString(), is(equalTo(json)));
  }

  @Test
  void testSingleAssist_ById_ShouldReturnAssist() throws Exception {
    Long pathVariable = 1L;

    when(assistService.getEntityById(pathVariable)).thenReturn((assist1));
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/assists/{id}", pathVariable))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getContentAsString(), containsString(assist1.request()));
  }

  @Test
  void testStoreEntity_ShouldReturn201() {
    RequestDTORequest dto = new RequestDTORequest("New request", 1L, 10L);
    when(service.storeEntity(dto)).thenReturn(mockResponse);

    ResponseEntity<RequestDTOResponse> response = controller.storeEntity(dto);

    assertThat(response.getStatusCode().value(), is(equalTo(201)));
  }

  @Test
  void testStoreEntity_ShouldReturn400_WhenDescriptionBlank() {
    RequestDTORequest dto = new RequestDTORequest("", 1L, 10L);

    ResponseEntity<RequestDTOResponse> response = controller.storeEntity(dto);

    assertThat(response.getStatusCode().value(), is(equalTo(400)));
  }

  @Test
  void testStoreEntity_ShouldReturn400_WhenUserIdNull() {
    RequestDTORequest dto = new RequestDTORequest("Valid description", null, 10L);

    ResponseEntity<RequestDTOResponse> response = controller.storeEntity(dto);

    assertThat(response.getStatusCode().value(), is(equalTo(400)));
  }

  @Test
  void testStoreEntity_ShouldReturn400_WhenTopicIdNull() {
    RequestDTORequest dto = new RequestDTORequest("Valid description", 1L, null);

    ResponseEntity<RequestDTOResponse> response = controller.storeEntity(dto);

    assertThat(response.getStatusCode().value(), is(equalTo(400)));
  }

  @Test
  void testStoreEntity_ShouldReturn204_WhenServiceReturnsNull() {
    RequestDTORequest dto = new RequestDTORequest("Valid description", 1L, 10L);
    when(service.storeEntity(dto)).thenReturn(null);

    ResponseEntity<RequestDTOResponse> response = controller.storeEntity(dto);

    assertThat(response.getStatusCode().value(), is(equalTo(204)));
  }
}
