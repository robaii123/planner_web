// File: service/TaskScheduleService.java
package com.example.planner_service.service;

import com.example.planner_service.domain.TaskSchedule;
import com.example.planner_service.repository.EmployeeRepository;
import com.example.planner_service.repository.TaskRepository;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class TaskScheduleService {

    private final SolverManager<TaskSchedule, UUID> solverManager;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskScheduleService(SolverManager<TaskSchedule, UUID> solverManager,
                               TaskRepository taskRepository,
                               EmployeeRepository employeeRepository) {
        this.solverManager = solverManager;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public TaskSchedule solve(TaskSchedule problem) {
        UUID problemId = UUID.randomUUID();
        try {
            TaskSchedule solution = solverManager.solve(problemId, problem).getFinalBestSolution();

            // Save employees if not already in DB
            solution.getEmployeeList().forEach(employeeRepository::save);

            // Save assigned tasks
            solution.getTaskList().forEach(taskRepository::save);

            return solution;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Solving failed.", e);
        }
    }
}
