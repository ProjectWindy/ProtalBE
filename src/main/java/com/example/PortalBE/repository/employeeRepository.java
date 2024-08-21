package com.example.PortalBE.repository;



import com.example.PortalBE.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<Employee, Long> {}
