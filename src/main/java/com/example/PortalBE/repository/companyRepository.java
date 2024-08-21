package com.example.PortalBE.repository;

import com.example.PortalBE.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface companyRepository extends JpaRepository<Company, Integer> {

}
