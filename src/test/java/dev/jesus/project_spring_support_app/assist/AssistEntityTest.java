package dev.jesus.project_spring_support_app.assist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.jesus.project_spring_support_app.request.RequestEntity;
import dev.jesus.project_spring_support_app.topic.TopicEntity;
import dev.jesus.project_spring_support_app.user.UserEntity;

public class AssistEntityTest {

  private AssistEntity assist;
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
    assist = new AssistEntity(1L, user, request);
  }

  @Test
  void testAssistEntity_InitializationWithIdUserRequest() {

    assertThat(assist, is(instanceOf(AssistEntity.class)));
    assertThat(assist.getClass().getDeclaredFields().length, is(equalTo(4)));
    assertNotNull(assist.getAssistDate());
    assertThat(assist.getRequest(), is(equalTo(request)));
    assertThat(assist.getUser(), is(equalTo(user)));
  }

  @Test
  void testAssistEntity() {
    RequestEntity request2 = new RequestEntity();
    UserEntity user2 = new UserEntity();

    assist.setId(2L);
    assist.setUser(user2);
    assist.setRequest(request2);
    assertThat(assist.getId(), is(equalTo(2L)));
    assertThat(assist.getRequest(), is(equalTo(request2)));
    assertThat(assist.getUser(), is(equalTo(user2)));
  }
}
