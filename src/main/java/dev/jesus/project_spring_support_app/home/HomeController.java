package dev.jesus.project_spring_support_app.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

  @GetMapping("")
  public String index() {

    String response = """
        Welcome to the support API from Spring Boot!
        If you want to search in the API you can look for:
        users: api...
        """;

    return response;
  }

}
