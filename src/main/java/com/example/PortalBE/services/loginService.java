package com.example.PortalBE.services;

import com.example.PortalBE.dto.LoginRequest;
import com.example.PortalBE.dto.LoginResponse;
import com.example.PortalBE.dto.RegistrationRequest;
import com.example.PortalBE.dto.RegistrationResponse;
import org.springframework.transaction.annotation.Transactional;

public interface loginService {
    @Transactional
    LoginResponse handleLogin(LoginRequest request);
    @Transactional
    RegistrationResponse registerUser(RegistrationRequest request);
}
