package dev.jesus.project_spring_support_app.globals;

public class RequestExceptionNotFound extends RequestException {

  public RequestExceptionNotFound(String message) {
    super(message);
  }

  public RequestExceptionNotFound() {
    super("La solicitud no fue encontrada");
  }
}