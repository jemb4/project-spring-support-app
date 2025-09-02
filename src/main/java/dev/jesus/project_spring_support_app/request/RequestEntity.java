package dev.jesus.project_spring_support_app.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;
import jakarta.persistence.Entity;
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
  boolean is_assisted;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "topic_id")
  private TopicEntity topic;

}
