package dev.jesus.project_spring_support_app.country;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CountryController {

  @GetMapping(path = "${api-endpoint}/countries")
  public CountryEntity index() {

    CountryEntity france = new CountryEntity(1L, "France");

    return france;
  }
}
