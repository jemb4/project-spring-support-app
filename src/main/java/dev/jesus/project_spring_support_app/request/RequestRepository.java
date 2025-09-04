package dev.jesus.project_spring_support_app.request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

  @Query("SELECT r FROM RequestEntity r ORDER BY r.request_date ASC")
  List<RequestEntity> findAllByOrderByDateAsc();
}