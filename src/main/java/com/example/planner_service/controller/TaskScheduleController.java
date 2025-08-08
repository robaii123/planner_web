package com.example.planner_service.controller;

import com.example.planner_service.domain.Employee;
import com.example.planner_service.domain.Task;
import com.example.planner_service.domain.TaskSchedule;
import com.example.planner_service.dto.EmployeeDTO;
import com.example.planner_service.dto.TaskDTO;
import com.example.planner_service.dto.TaskScheduleDTO;
import com.example.planner_service.service.TaskScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/planning")
@Tag(name = "Planning API", description = "Solve task assignment problems")
public class TaskScheduleController {

    private final TaskScheduleService taskScheduleService;

    public TaskScheduleController(TaskScheduleService taskScheduleService) {
        this.taskScheduleService = taskScheduleService;
    }

    @Operation(summary = "Solve task assignment")
    @PostMapping(value = "/solve", produces = "application/json")
    public ResponseEntity<TaskScheduleDTO> solve(@RequestBody TaskScheduleDTO problemDTO) {

        // Convert DTO to domain
        TaskSchedule problem = convertDTOtoDomain(problemDTO);

        // Solve the problem explicitly
        TaskSchedule solution = taskScheduleService.solve(problem);

        // Convert domain model solution back to DTO clearly
        TaskScheduleDTO solutionDTO = convertDomainToDTO(solution);

        return ResponseEntity.ok(solutionDTO);
    }

    // Conversion methods explicitly and clearly defined below:

    private TaskSchedule convertDTOtoDomain(TaskScheduleDTO dto) {
        List<Employee> employees = dto.getEmployeeList().stream()
                .map(e -> new Employee(e.getName()))
                .toList();

        List<Task> tasks = dto.getTaskList().stream()
                .map(t -> {
                    Task task = new Task(t.getId(), t.getTaskName());
                    // Set assignedEmployee (OptaPlanner will override it, but still needed)
                    if (t.getAssignedEmployeeName() != null) {
                        task.setAssignedEmployee(
                                employees.stream()
                                        .filter(e -> e.getName().equals(t.getAssignedEmployeeName()))
                                        .findFirst()
                                        .orElse(null)
                        );
                    }

                    // Set preferredEmployee (this is what your constraint uses)
                    if (t.getPreferredEmployeeName() != null) {
                        task.setPreferredEmployee(
                                employees.stream()
                                        .filter(e -> e.getName().equals(t.getPreferredEmployeeName()))
                                        .findFirst()
                                        .orElse(null)
                        );
                    }

                    return task;
                }).toList();

        return new TaskSchedule(employees, tasks);
    }




    private TaskScheduleDTO convertDomainToDTO(TaskSchedule solution) {
        List<EmployeeDTO> employees = solution.getEmployeeList().stream()
                .map(e -> new EmployeeDTO(e.getName()))
                .toList();

        List<TaskDTO> tasks = solution.getTaskList().stream()
                .map(t -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(t.getId());
                    taskDTO.setTaskName(t.getTaskName());

                    if (t.getAssignedEmployee() != null) {
                        taskDTO.setAssignedEmployeeName(t.getAssignedEmployee().getName());
                    }

                    if (t.getPreferredEmployee() != null) {
                        taskDTO.setPreferredEmployeeName(t.getPreferredEmployee().getName());
                    }

                    return taskDTO;
                })
                .toList();

        return new TaskScheduleDTO(employees, tasks, solution.getScore().toString());
    }

}



