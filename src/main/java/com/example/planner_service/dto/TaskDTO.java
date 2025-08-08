package com.example.planner_service.dto;

public class TaskDTO {
    private String id;
    private String taskName;
    private String assignedEmployeeName;
    private String preferredEmployeeName;

    public TaskDTO() {}

    public TaskDTO(String id, String taskName, String assignedEmployeeName,String preferredEmployeeName) {
        this.id = id;
        this.taskName = taskName;
        this.assignedEmployeeName = assignedEmployeeName;
        this.preferredEmployeeName = preferredEmployeeName;
    }

    public String getPreferredEmployeeName() {
        return preferredEmployeeName;
    }

    public void setPreferredEmployeeName(String preferredEmployeeName) {
        this.preferredEmployeeName = preferredEmployeeName;
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

    public String getAssignedEmployeeName() {
        return assignedEmployeeName;
    }

    public void setAssignedEmployeeName(String assignedEmployeeName) {
        this.assignedEmployeeName = assignedEmployeeName;
    }
}
