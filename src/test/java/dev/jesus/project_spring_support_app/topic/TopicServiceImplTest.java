package dev.jesus.project_spring_support_app.topic;

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

import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.topic.dtos.TopicDTORequest;
import dev.jesus.project_spring_support_app.topic.dtos.TopicDTOResponse;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {
  @InjectMocks
  private TopicServiceImpl service;

  @Mock
  private TopicRepository repository;

  private TopicEntity mockEntity;

  @BeforeEach
  void setUp() {
    mockEntity = new TopicEntity();
    mockEntity.setId(1L);
    mockEntity.setName("Tech Support");
  }

  @Test
  void testGetEntities_ShouldReturnAllTopics() {
    when(repository.findAll()).thenReturn(List.of(mockEntity));

    List<TopicDTOResponse> result = service.getEntities();

    assertThat(result.size(), is(equalTo(1)));
    assertThat(result.get(0).name(), is(equalTo("Tech Support")));
  }

  @Test
  void testGetEntities_ShouldReturnEmptyList() {
    when(repository.findAll()).thenReturn(List.of());

    List<TopicDTOResponse> result = service.getEntities();

    assertThat(result.size(), is(equalTo(0)));
  }

  @Test
  void testStoreEntity_ShouldSaveAndReturnDTO() {
    TopicDTORequest dto = new TopicDTORequest("New Topic", "info");
    when(repository.save(any(TopicEntity.class))).thenReturn(mockEntity);

    TopicDTOResponse result = service.storeEntity(dto);

    assertThat(result.name(), is(equalTo("Tech Support")));
    verify(repository, times(1)).save(any(TopicEntity.class));
  }

  @Test
  void testGetEntityById_ShouldReturnTopic_WhenExists() {
    when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));

    TopicDTOResponse result = service.getEntityById(1L);

    assertThat(result.name(), is(equalTo("Tech Support")));
  }

  @Test
  void testGetEntityById_ShouldThrowException_WhenNotExists() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    try {
      service.getEntityById(99L);
    } catch (RequestExceptionNotFound ex) {
      assertThat(ex.getMessage(), is(equalTo("Request with id 99 not exist.")));
    }
  }
}
