package com.example.planner_service.dto;

public class EmployeeDTO {
    private String name;

    public EmployeeDTO() {}

    public EmployeeDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

