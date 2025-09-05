package dev.jesus.project_spring_support_app.assist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.jesus.project_spring_support_app.assist.dtos.AssistDTORequest;
import dev.jesus.project_spring_support_app.assist.dtos.AssistDTOResponse;
import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.request.RequestServiceImpl;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;
import dev.jesus.project_spring_support_app.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AssistServiceImplTest {

  @InjectMocks
  private AssistServiceImpl assistService;

  @Mock
  private AssistRepository repository;

  @Mock
  private UserServiceImpl userService;

  @Mock
  private RequestServiceImpl requestService;

  private UserEntity mockUser;
  private RequestEntity mockRequest;
  private AssistEntity mockAssist;

  @BeforeEach
  void setUp() {
    mockUser = new UserEntity();
    mockUser.setId(1L);

    TopicEntity mockTopic = new TopicEntity();
    mockTopic.setId(10L);
    mockTopic.setName("Mock Topic");

    mockRequest = new RequestEntity();
    mockRequest.setId(100L);
    mockRequest.setDescription("Initial request");
    mockRequest.setUser(mockUser);
    mockRequest.setTopic(mockTopic);

    mockAssist = new AssistEntity();
    mockAssist.setId(10L);
    mockAssist.setUser(mockUser);
    mockAssist.setRequest(mockRequest);
  }

  @Test
  void testGetEntities_ShouldReturnAllAssists() {
    List<AssistEntity> assists = new ArrayList<>();
    assists.add(mockAssist);

    when(repository.findAll()).thenReturn(assists);

    List<AssistDTOResponse> result = assistService.getEntities();

    assertThat(result).hasSize(1);
    assertThat(result.get(0).id()).isEqualTo(mockAssist.getId());
  }

  @Test
  void testStoreEntity_ShouldSaveAndReturnDTO() {
    AssistDTORequest dto = new AssistDTORequest(mockUser.getId(), mockRequest.getId());

    when(userService.getUserEntityById(mockUser.getId())).thenReturn(mockUser);
    when(requestService.getRequestEntityById(mockRequest.getId())).thenReturn(mockRequest);
    when(repository.save(any(AssistEntity.class))).thenReturn(mockAssist);

    AssistDTOResponse result = assistService.storeEntity(dto);

    assertThat(result.id()).isEqualTo(mockAssist.getId());
    verify(repository, times(1)).save(any(AssistEntity.class));
  }

  @Test
  void testGetEntityById_ShouldReturnDTO() {
    when(repository.findById(mockAssist.getId())).thenReturn(Optional.of(mockAssist));

    AssistDTOResponse result = assistService.getEntityById(mockAssist.getId());

    assertThat(result.id()).isEqualTo(mockAssist.getId());
  }

  @Test
  void testGetEntityById_ShouldThrowException_WhenNotFound() {
    when(repository.findById(999L)).thenReturn(Optional.empty());

    try {
      assistService.getEntityById(999L);
    } catch (RequestExceptionNotFound ex) {
      assertThat(ex.getMessage()).isEqualTo("Assist with id 999 not exist.");
    }
  }
}
