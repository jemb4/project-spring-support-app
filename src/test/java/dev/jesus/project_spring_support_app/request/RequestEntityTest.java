package dev.jesus.project_spring_support_app.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

public class RequestEntityTest {

  private RequestEntity request;
  private TopicEntity topic;
  private UserEntity user;

  @BeforeEach
  void setUp() {
    user = UserEntity.builder()
        .id(1L)
        .name("Peter")
        .build();

    topic = new TopicEntity(1L, "topic", "description");
    request = new RequestEntity(1L, "error blue screen", user, topic);
  }

  @Test
  void testRequestEntity_InitializationWithIdNameUserAndTopic() {

    assertThat(request, is(instanceOf(RequestEntity.class)));
    assertThat(request.getClass().getDeclaredFields().length, is(equalTo(7)));
    assertNotNull(request.getRequest_date());
    assertFalse(request.is_assisted());
    assertThat(request.getDescription(), is(equalTo("error blue screen")));
    assertThat(request.getTopic(), is(equalTo(topic)));
    assertThat(request.getUser(), is(equalTo(user)));
  }

  @Test
  void testRequestEntity() {
    TopicEntity topic2 = new TopicEntity();
    UserEntity user2 = new UserEntity();

    request.setId(2L);
    request.setDescription("Peter");
    request.setUser(user2);
    request.setTopic(topic2);
    assertThat(request.getId(), is(equalTo(2L)));
    assertThat(request.getDescription(), is(equalTo("Peter")));
    assertThat(request.getTopic(), is(equalTo(topic2)));
    assertThat(request.getUser(), is(equalTo(user2)));
  }
}
