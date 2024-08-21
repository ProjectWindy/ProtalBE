package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {
    private int id;
    private String jobCode;
    private String jobName;
    private Double salary;
}
