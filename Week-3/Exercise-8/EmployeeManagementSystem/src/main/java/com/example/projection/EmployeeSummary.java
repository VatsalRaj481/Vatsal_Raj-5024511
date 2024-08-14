package com.example.projection;

public class EmployeeSummary {
    private String name;
    private String email;

    public EmployeeSummary(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}