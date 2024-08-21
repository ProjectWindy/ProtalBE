package com.example.PortalBE.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

@Entity
@Getter
@Setter
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long candidateId;
    private YearMonth month;
    private LocalDate date;
    private String projectName;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String taskName;
    private double regularHours;
    private double overtimeHours;
    private boolean approved;

    // Constructors, getters, and setters

    public TimeSheet() {}

    public TimeSheet(Long id, Long candidateId, YearMonth month, LocalDate date, String projectName, LocalTime fromTime, LocalTime toTime, String taskName, double regularHours, double overtimeHours, boolean approved) {
        this.id = id;
        this.candidateId = candidateId;
        this.month = month;
        this.date = date;
        this.projectName = projectName;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.taskName = taskName;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.approved = approved;
    }

    // Getters and Setters
    // ...
}
