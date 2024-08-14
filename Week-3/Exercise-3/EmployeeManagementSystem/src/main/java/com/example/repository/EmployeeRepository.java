package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by their name
    List<Employee> findByName(String name);

    // Find employees by their email
    Employee findByEmail(String email);

    // Find employees by department id
    List<Employee> findByDepartmentId(Long departmentId);
}
