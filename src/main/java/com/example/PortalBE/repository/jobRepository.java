package com.example.PortalBE.repository;

import com.example.PortalBE.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jobRepository extends JpaRepository <Job, Integer> {

}
