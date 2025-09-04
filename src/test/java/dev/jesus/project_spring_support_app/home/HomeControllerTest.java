package dev.jesus.project_spring_support_app.home;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

public class HomeControllerTest {

  @Test
  void index() {
    HomeController homeController = new HomeController();
    String result = homeController.index();

    assertThat(result, containsString("If you want to search in the API you can look for:"));
  }
}
