package com.example.PortalBE.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CompanyRequest extends CompanyInformationDTO {
    private MultipartFile imageInfor;
}
