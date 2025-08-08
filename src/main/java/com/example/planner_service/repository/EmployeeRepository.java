package com.example.planner_service.repository;

import com.example.planner_service.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee,String>{
}
