package com.example.PortalBE.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ManagerDTO {
    private int id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String mail;
    private String image;
//    private List<CandidateDTO> listCandidate;

}
