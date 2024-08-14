package com.example.controller;

import com.example.entity.Employee;
import com.example.projection.EmployeeNameProjection;
import com.example.projection.EmployeeSummary;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(employee);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    // Pagination endpoint
    @GetMapping("/paginated")
    public Page<Employee> getPaginatedEmployees(
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    // Sorting endpoint
    @GetMapping("/sorted")
    public List<Employee> getSortedEmployees(@RequestParam String sortBy) {
        return employeeRepository.findAll(Sort.by(sortBy));
    }

    // Endpoint for interface-based projection
    @GetMapping("/names")
    public List<EmployeeNameProjection> getEmployeeNames() {
        return employeeRepository.findAllProjectedBy();
    }

    // Endpoint for class-based projection
    @GetMapping("/summaries")
    public List<EmployeeSummary> getEmployeeSummaries() {
        return employeeRepository.findAllEmployeeSummaries();
    }
}
