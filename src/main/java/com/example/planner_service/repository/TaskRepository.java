package com.example.planner_service.repository;

import com.example.planner_service.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,String> {
}
