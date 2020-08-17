package com.demo.repository;

import com.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
  List<Activity> findByNameContaining(String name);
}