package dev.jesus.project_spring_support_app.topic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopicEntityTest {

  private TopicEntity topic;

  @BeforeEach
  void setUp() {
    topic = new TopicEntity(1L, "topic name", "topic description");
  }

  @Test
  void testTopicEntity_InitializationWithIdandName() {

    assertThat(topic, is(instanceOf(TopicEntity.class)));
    assertThat(topic.getClass().getDeclaredFields().length, is(equalTo(4)));
    assertThat(topic.getName(), is(equalTo("topic name")));
    assertThat(topic.getId(), is(equalTo(1L)));
  }

  @Test
  void testRolEntity() {

    topic.setId(2L);
    topic.setName("topic");
    topic.setDescription("description");
    assertThat(topic.getId(), is(equalTo(2L)));
    assertThat(topic.getName(), is(equalTo("topic")));
    assertThat(topic.getDescription(), is(equalTo("description")));
  }

}
