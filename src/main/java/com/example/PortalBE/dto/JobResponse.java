package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobResponse {
    private int id;
    private String jobName;
    private String jobCode;
    private String description;
    private Double salary;
    private String category;
    private String requirement;
    private String image;
    private String hrName;
    private LocalDateTime createdAt;
}
