package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespone {
    private int id;
    private String email;
    private String password;
    private String role;
}
