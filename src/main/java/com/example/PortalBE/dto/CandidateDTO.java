package com.example.PortalBE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {
    private int id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String experience;
    private String category;
    private String cv;
    private List<JobDTO> listJobApply;
    private TimeSheetDTO timeSheets;
}
