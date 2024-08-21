package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int candidateId;

    // Nếu không sử dụng Lombok, bạn có thể viết getter và setter thủ công như sau:
    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public LocalDateTime getCheckInTime() {
    //     return checkInTime;
    // }

    // public void setCheckInTime(LocalDateTime checkInTime) {
    //     this.checkInTime = checkInTime;
    // }

    // public LocalDateTime getCheckOutTime() {
    //     return checkOutTime;
    // }

    // public void setCheckOutTime(LocalDateTime checkOutTime) {
    //     this.checkOutTime = checkOutTime;
    // }

    // public int getCandidateId() {
    //     return candidateId;
    // }

    // public void setCandidateId(int candidateId) {
    //     this.candidateId = candidateId;
    // }
}
