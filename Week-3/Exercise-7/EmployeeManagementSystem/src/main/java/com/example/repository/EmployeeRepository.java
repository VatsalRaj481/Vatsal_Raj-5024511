package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Method to support pagination
    Page<Employee> findAll(Pageable pageable);

    // Method to support sorting
    List<Employee> findAll(Sort sort);
}
