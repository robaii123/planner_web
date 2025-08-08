package com.example.planner_service.dto;

import java.util.List;

public class TaskScheduleDTO {
    private List<EmployeeDTO> employeeList;
    private List<TaskDTO> taskList;
    private String score;

    public TaskScheduleDTO() {}

    public TaskScheduleDTO(List<EmployeeDTO> employeeList, List<TaskDTO> taskList, String score) {
        this.employeeList = employeeList;
        this.taskList = taskList;
        this.score = score;
    }

    public List<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

