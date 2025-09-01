package dev.jesus.project_spring_support_app.rol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  private MockMvc MockMvc;

  @MockitoBean
  private IGenericService<RolDTOResponse, RolDTORequest> rolService;

  @Autowired
  ObjectMapper mapper;

  @Test
  void testIndex_ShouldReturnARols() {
    CountryDTOResponse france = new CountryDTOResponse(1L, "France");
    CountryDTOResponse italy = new CountryDTOResponse(1L, "Italy");
    List<CountryDTOResponse> countries = List.of(france, italy);
    String json = mapper.writeValueAsString(countries);

    when(countryService.getEntities()).thenReturn(countries);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    assertThat(response.getStatus(), is(equalTo(200)));
    assertThat(response.getContentAsString(), is(equalTo(json)));
  }

  @Test
  void testStore_ShouldReturnStatus201() throws Exception {
    CountryDTORequest dto = new CountryDTORequest("France");
    CountryDTOResponse france = new CountryDTOResponse(1L, "France");
    String json = mapper.writeValueAsString(dto);

    when(countryService.storeEntity(dto)).thenReturn(france);
    MockHttpServletResponse response = mockMvc
        .perform(post("/api/v1/countries").content(json).contentType("application/json"))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse();

    assertThat(response.getContentAsString(), containsString(france.name()));
  }

  @Test
  void testStoreCountry_ShouldReturnStatus400_IfNameIsEmpty() throws Exception {
    CountryDTORequest dto = new CountryDTORequest("");
    String json = mapper.writeValueAsString(dto);
    when(countryService.storeEntity(dto)).thenReturn(null);
    mockMvc.perform(post("/api/v1/countries").content(json).contentType("application/json"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testStoreCountry_ShouldReturnNoContent_IfServiceDoesNotReturnAnyValue() throws Exception {
    CountryDTORequest dto = new CountryDTORequest("France");
    String json = mapper.writeValueAsString(dto);

    when(countryService.storeEntity(dto)).thenReturn(null);
    mockMvc.perform(post("/api/v1/countries").content(json).contentType("application/json"))
        .andExpect(status().isNoContent());
  }
}
