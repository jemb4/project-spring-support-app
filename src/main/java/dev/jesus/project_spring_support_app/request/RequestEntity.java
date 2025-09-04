package dev.jesus.project_spring_support_app.request;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import dev.jesus.project_spring_support_app.assist.AssistEntity;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "requests")
public class RequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  LocalDate request_date;
  String description;
  boolean assisted;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "topic_id")
  private TopicEntity topic;

  @JsonManagedReference
  @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
  private List<AssistEntity> assists;

  public RequestEntity() {
  }

  public RequestEntity(Long id, String description, UserEntity user,
      TopicEntity topic) {
    this.id = id;
    setRequest_date(LocalDate.now());
    setAssisted(false);
    this.description = description;
    this.user = user;
    this.topic = topic;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getRequest_date() {
    return request_date;
  }

  public void setRequest_date(LocalDate request_date) {
    this.request_date = request_date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean is_assisted() {
    return assisted;
  }

  public void setAssisted(boolean assisted) {
    this.assisted = assisted;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public TopicEntity getTopic() {
    return topic;
  }

  public void setTopic(TopicEntity topic) {
    this.topic = topic;
  }

  public List<AssistEntity> getAssists() {
    return assists;
  }

  public void setAssists(List<AssistEntity> assists) {
    this.assists = assists;
  }

}
