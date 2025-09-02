package dev.jesus.project_spring_support_app.topic;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.jesus.project_spring_support_app.request.RequestEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class TopicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  String name;
  String description;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "topic_id")
  private TopicEntity topic;

  public TopicEntity() {
  }

  public TopicEntity(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TopicEntity getTopic() {
    return topic;
  }

  public void setTopic(TopicEntity topic) {
    this.topic = topic;
  }

}
