package dev.jesus.project_spring_support_app.implementations;

import java.util.List;

public interface IGenericService<T, S> {

  public List<T> getEntities();

  public T storeEntity(S dto);
}