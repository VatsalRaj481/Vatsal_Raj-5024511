package com.example.repository;

import com.example.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	// Find department by name
    Department findByName(String name);

    // Find all departments with a specific name (if needed)
    List<Department> findAllByName(String name);
}
