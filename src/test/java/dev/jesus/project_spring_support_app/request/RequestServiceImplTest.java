package dev.jesus.project_spring_support_app.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.jesus.project_spring_support_app.assist.AssistServiceImpl;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTORequest;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOResponse;
import dev.jesus.project_spring_support_app.request.dtos.RequestDTOUpdate;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.topic.TopicRepository;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RequestServiceImplTest {

  @InjectMocks
  private RequestServiceImpl requestService;

  @Mock
  private RequestRepository requestRepository;

  @Mock
  private UserServiceImpl userService;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private AssistServiceImpl assistService;

  private UserEntity mockUser;
  private TopicEntity mockTopic;
  private RequestEntity mockRequest;

  @BeforeEach
  void setUp() {
    mockUser = new UserEntity();
    mockUser.setId(1L);

    mockTopic = new TopicEntity();
    mockTopic.setId(10L);

    mockRequest = new RequestEntity();
    mockRequest.setId(100L);
    mockRequest.setDescription("Initial request");
    mockRequest.setUser(mockUser);
    mockRequest.setTopic(mockTopic);
  }

  @Test
  void testGetEntities_ShouldReturnAllRequests() {
    when(requestRepository.findAllByOrderByDateAsc()).thenReturn(List.of(mockRequest));

    List<RequestDTOResponse> result = requestService.getEntities();

    assertThat(result.size(), is(equalTo(1)));
    assertThat(result.get(0).description(), is(equalTo("Initial request")));
  }

  @Test
  void testStoreEntity_ShouldSaveAndReturnDTO() {
    RequestDTORequest dto = new RequestDTORequest("New request", 1L, 10L);

    when(userService.getUserEntityById(1L)).thenReturn(mockUser);
    when(topicRepository.findById(10L)).thenReturn(Optional.of(mockTopic));
    when(requestRepository.save(any(RequestEntity.class))).thenReturn(mockRequest);

    RequestDTOResponse result = requestService.storeEntity(dto);

    assertThat(result.description(), is(equalTo("Initial request")));
    verify(requestRepository, times(1)).save(any(RequestEntity.class));
  }

  @Test
  void testGetEntityById_ShouldReturnDTO() {
    when(requestRepository.findById(100L)).thenReturn(Optional.of(mockRequest));

    RequestDTOResponse result = requestService.getEntityById(100L);

    assertThat(result.description(), is(equalTo("Initial request")));
  }

  @Test
  void testUpdateEntity_ShouldUpdateAndReturnDTO() {
    RequestDTOUpdate dtoUpdate = new RequestDTOUpdate("Updated description", 1L);

    when(requestRepository.findById(100L)).thenReturn(Optional.of(mockRequest));
    when(requestRepository.save(mockRequest)).thenReturn(mockRequest);

    RequestDTOResponse result = requestService.updateEntity(100L, dtoUpdate);

    assertThat(result.description(), is(equalTo("Updated description")));
    verify(assistService, times(1)).storeEntity(any(AssistDTORequest.class));
  }

  @Test
  void testDeleteEntity_ShouldDeleteIfAssisted() {
    mockRequest.setAssisted(true);
    when(requestRepository.findById(100L)).thenReturn(Optional.of(mockRequest));

    requestService.deleteEntity(100L);

    verify(requestRepository, times(1)).delete(mockRequest);
  }
}
