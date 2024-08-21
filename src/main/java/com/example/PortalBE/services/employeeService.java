package com.example.PortalBE.services;



import com.example.PortalBE.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface employeeService {
    Employee create(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);
    Employee findByid(Long id);
    Iterable<Employee> findAll();
}
