package dev.jesus.project_spring_support_app.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOUpdate;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

  @InjectMocks
  private RequestController controller;

  @Mock
  private IRequestService<RequestDTOResponse, RequestDTORequest> service;

  private RequestDTOResponse mockResponse;

  @BeforeEach
  void setUp() {
    mockResponse = new RequestDTOResponse(
        100L,
        "Initial request",
        "User One",
        "Topic One",
        true,
        LocalDate.now());
  }

  @Test
  void testIndex_ShouldReturnAllRequests() {
    when(service.getEntities()).thenReturn(List.of(mockResponse));

    List<RequestDTOResponse> result = controller.index();

    assertThat(result.size(), is(equalTo(1)));
    assertThat(result.get(0).description(), is(equalTo("Initial request")));
  }

  @Test
  void testIndex_ShouldReturnEmptyList() {
    when(service.getEntities()).thenReturn(Collections.emptyList());

    List<RequestDTOResponse> result = controller.index();

    assertThat(result.size(), is(equalTo(0)));
  }

  @Test
  void testSingleRequest_ShouldReturnRequestById() {
    when(service.getEntityById(100L)).thenReturn(mockResponse);

    ResponseEntity<RequestDTOResponse> response = controller.SingleRequest(100L);

    assertThat(response.getStatusCode().value(), is(equalTo(200)));
    assertThat(response.getBody().description(), is(equalTo("Initial request")));
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

  @Test
  void testUpdateRequest_ShouldReturn200() {
    RequestDTOUpdate dtoUpdate = new RequestDTOUpdate("Updated description", 1L);
    when(service.updateEntity(100L, dtoUpdate)).thenReturn(
        new RequestDTOResponse(100L, "Updated description", "User One", "Topic One", true, LocalDate.now()));

    ResponseEntity<RequestDTOResponse> response = controller.updateRequest(100L, dtoUpdate);

    assertThat(response.getStatusCode().value(), is(equalTo(200)));
    assertThat(response.getBody().description(), is(equalTo("Updated description")));
  }

  @Test
  void testUpdateRequest_ShouldReturn200_WhenServiceReturnsNull() {
    RequestDTOUpdate dtoUpdate = new RequestDTOUpdate("Updated description", 1L);
    when(service.updateEntity(100L, dtoUpdate)).thenReturn(null);

    ResponseEntity<RequestDTOResponse> response = controller.updateRequest(100L, dtoUpdate);

    assertThat(response.getStatusCode().value(), is(equalTo(200)));
    assertThat(response.getBody(), is(equalTo(null)));
  }

  @Test
  void testDeleteRequest_ShouldReturn204() {
    doNothing().when(service).deleteEntity(100L);

    ResponseEntity<Void> response = controller.deleteRequest(100L);

    assertThat(response.getStatusCode().value(), is(equalTo(204)));
  }

  @Test
  void testDeleteRequest_ShouldReturn400_WhenIllegalState() {
    doThrow(new IllegalStateException("Not assisted")).when(service).deleteEntity(100L);

    ResponseEntity<Void> response = controller.deleteRequest(100L);

    assertThat(response.getStatusCode().value(), is(equalTo(400)));
  }
}
