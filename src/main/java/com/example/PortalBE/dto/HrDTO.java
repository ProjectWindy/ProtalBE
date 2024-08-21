package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HrDTO {
    private int id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String mail;
    private String position;
    private String image;
    private List<JobDTO> listJob;

}
