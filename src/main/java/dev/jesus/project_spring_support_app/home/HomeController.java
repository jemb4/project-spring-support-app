package dev.jesus.project_spring_support_app.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

  @GetMapping("")
  public RedirectView index() {

    return new RedirectView("/swagger-ui.html");
  }

}
