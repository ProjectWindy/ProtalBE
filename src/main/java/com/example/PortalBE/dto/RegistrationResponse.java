package com.example.PortalBE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponse {
    private String message;
    private UserRespone user;
}
