package com.example.repository;

import com.example.entity.Employee;
import com.example.projection.EmployeeNameProjection;
import com.example.projection.EmployeeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Interface-based projection
    List<EmployeeNameProjection> findAllProjectedBy();
    
    // Class-based projection with constructor expression
    @Query("SELECT new com.example.projection.EmployeeSummary(e.name, e.email) FROM Employee e")
    List<EmployeeSummary> findAllEmployeeSummaries();
}
