package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bookstore.entity.Customer;
import com.bookstore.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Register a new customer with query parameters
    @PostMapping("/register")
    public String registerCustomer(@RequestParam String name, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customerRepository.save(customer);
        return "Customer registered successfully!";
    }

    // Fetch a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null); // Return null if customer not found
    }

    // Fetch all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update a customer's details
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    return customerRepository.save(customer);
                })
                .orElse(null); // Return null if customer not found
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    // Fetch a customer by email
    @GetMapping("/email")
    public Customer getCustomerByEmail(@RequestParam String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
}
