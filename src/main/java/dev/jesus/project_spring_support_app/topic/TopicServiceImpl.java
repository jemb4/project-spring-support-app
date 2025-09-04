package dev.jesus.project_spring_support_app.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jesus.project_spring_support_app.globals.RequestExceptionNotFound;
import dev.jesus.project_spring_support_app.implementations.IGenericService;
import dev.jesus.project_spring_support_app.topic.dtos.TopicDTORequest;
import dev.jesus.project_spring_support_app.topic.dtos.TopicDTOResponse;
import dev.jesus.project_spring_support_app.topic.mappers.TopicMapper;

@Service
public class TopicServiceImpl implements IGenericService<TopicDTOResponse, TopicDTORequest> {

  private TopicRepository repository;

  public TopicServiceImpl(TopicRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<TopicDTOResponse> getEntities() {
    List<TopicDTOResponse> topics = new ArrayList<>();

    repository.findAll().forEach(c -> {
      TopicDTOResponse topic = TopicMapper.toDTO(c);
      topics.add(topic);
    });

    return topics;
  }

  @Override
  public TopicDTOResponse storeEntity(TopicDTORequest dtoTopic) {
    TopicEntity topic = TopicMapper.toEntity(dtoTopic);
    TopicEntity topicStored = repository.save(topic);
    return TopicMapper.toDTO(topicStored);
  }

  public TopicDTOResponse getEntityById(Long id) {
    TopicEntity topic = repository.findById(id)
        .orElseThrow(() -> new RequestExceptionNotFound("Request with id " + id + " not exist."));
    return TopicMapper.toDTO(topic);
  }
}
