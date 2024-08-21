package com.example.PortalBE.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CandidateRequest extends CandidateDTO {
    private MultipartFile imageCV;
}
