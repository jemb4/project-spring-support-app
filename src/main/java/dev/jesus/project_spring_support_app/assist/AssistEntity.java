package dev.jesus.project_spring_support_app.assist;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assists")
public class AssistEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  LocalDate assistDate;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "request_id")
  private RequestEntity request;

  public AssistEntity() {
  }

  public AssistEntity(Long id, UserEntity user, RequestEntity request) {
    this.id = id;
    this.user = user;
    this.request = request;
    setAssistDate(LocalDate.now());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getAssistDate() {
    return assistDate;
  }

  public void setAssistDate(LocalDate assistDate) {
    this.assistDate = assistDate;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public RequestEntity getRequest() {
    return request;
  }

  public void setRequest(RequestEntity request) {
    this.request = request;
  }

}
