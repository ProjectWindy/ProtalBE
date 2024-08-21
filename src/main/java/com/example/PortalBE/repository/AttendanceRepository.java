//package com.example.PortalBE.repository;
//
//import com.example.PortalBE.entity.Attendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//
//    @Query("SELECT a FROM Attendance a WHERE a.candidate.id = :candidateId AND FUNCTION('DATE', a.checkInTime) = :date")
//    List<Attendance> findByCandidateIdAndDate(@Param("candidateId") int candidateId, @Param("date") LocalDate date);
//    List<Attendance> findByCandidateId(int candidateId);
//
//}

package com.example.PortalBE.repository;

import com.example.PortalBE.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.candidate.id = :candidateId AND FUNCTION('DATE', a.checkInTime) = :date")
    List<Attendance> findByCandidateIdAndDate(@Param("candidateId") int candidateId, @Param("date") LocalDate date);

    List<Attendance> findByCandidateId(int candidateId);
}
