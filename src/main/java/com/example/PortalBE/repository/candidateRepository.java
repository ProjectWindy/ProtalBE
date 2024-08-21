package com.example.PortalBE.repository;

import com.example.PortalBE.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface candidateRepository extends JpaRepository<Candidate, Integer> {
//    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.appliedJobs WHERE c.id = :id")
//    Candidate findCandidateWithAppliedJobs(@Param("id") Integer id);

}

