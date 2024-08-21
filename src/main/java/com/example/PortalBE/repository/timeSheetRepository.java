package com.example.PortalBE.repository;

import com.example.PortalBE.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface timeSheetRepository extends JpaRepository<TimeSheet, Long> {
    List<TimeSheet> findByCandidateIdAndDateBetween(Long candidateId, LocalDate startDate, LocalDate endDate);
}
