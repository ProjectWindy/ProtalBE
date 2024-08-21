package com.example.PortalBE.services.Impl;

import com.example.PortalBE.entity.Employee;
import com.example.PortalBE.repository.employeeRepository;
import com.example.PortalBE.services.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class employeeServiceImpl implements employeeService {
    @Autowired
    private employeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        if (employee.getMail() == null  || employee.getMail().isEmpty()) {
            return null;
        }
        if (employee.getName() == null  || employee.getName().isEmpty()) {
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee fromDB = employeeRepository.findById(id).orElse(null);
        if (fromDB == null) {
            return null;
        }
        fromDB.setDepartment(employee.getDepartment());
        fromDB.setName(employee.getName());
        fromDB.setPosition(employee.getPosition());
        fromDB.setMail(employee.getMail());
        fromDB.setPhone(employee.getPhone());
        return employeeRepository.save(fromDB);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findByid(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
