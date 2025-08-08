// File: domain/TaskSchedule.java
package com.example.planner_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSchedule {

    @ValueRangeProvider(id = "employeeRange")
    @ProblemFactCollectionProperty
    @JsonProperty("employeeList")
    private List<Employee> employeeList;

    @PlanningEntityCollectionProperty
    @JsonProperty("taskList")
    private List<Task> taskList;

    @PlanningScore
    @JsonProperty("score")
    private HardSoftScore score;

    public TaskSchedule() {}

    public TaskSchedule(List<Employee> employeeList, List<Task> taskList) {
        this.employeeList = employeeList;
        this.taskList = taskList;
    }

    // Getters and setters
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}

