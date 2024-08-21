package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
@Getter
@Setter
public class TimeSheetDTO {

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

    // Constructors
    public TimeSheetDTO() {}

    public TimeSheetDTO(Long id, Long candidateId, YearMonth month, LocalDate date, String projectName, LocalTime fromTime, LocalTime toTime, String taskName, double regularHours, double overtimeHours, boolean approved) {
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
}
