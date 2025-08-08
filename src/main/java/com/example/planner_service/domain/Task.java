package com.example.planner_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import jakarta.persistence.*;


@Entity
@PlanningEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @Id
    @PlanningId
    @JsonProperty("id")
    private String id;

    @JsonProperty("taskName")
    private String taskName;

    @ManyToOne(cascade = CascadeType.ALL)
    @PlanningVariable(valueRangeProviderRefs = "employeeRange")
    @JsonProperty("assignedEmployee")
    private Employee assignedEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee preferredEmployee;

    public Task() {}

    public Task(String id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

    // Getters and setters

    public Employee getPreferredEmployee() {
        return preferredEmployee;
    }

    public void setPreferredEmployee(Employee preferredEmployee) {
        this.preferredEmployee = preferredEmployee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
}

