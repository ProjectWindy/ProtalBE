package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class JobRequest {
    private int id;
    private String jobName;
    private String jobCode;
    private String description;
    private Double salary;
    private String category;
    private String requirement;
    private MultipartFile imageJob;
    private int HR_id;
}
