package com.example.PortalBE.repository;

import com.example.PortalBE.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface managerRepository extends JpaRepository<Manager, Integer> {
}
