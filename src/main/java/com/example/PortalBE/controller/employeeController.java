package com.example.PortalBE.controller;

import com.example.PortalBE.entity.Employee;
import com.example.PortalBE.services.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class employeeController {

    @Autowired
    private employeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee create = employeeService.create(employee);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
    @GetMapping("/")
    public Iterable<Employee> findAll() {
        return employeeService.findAll();
    }
    @GetMapping("/{id}")
    public Employee findByID(@PathVariable Long id) {
        return employeeService.findByid(id);
    }
}
