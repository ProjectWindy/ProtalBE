package com.example.PortalBE.controller;

import com.example.PortalBE.dto.LoginRequest;
import com.example.PortalBE.dto.LoginResponse;
import com.example.PortalBE.dto.RegistrationRequest;
import com.example.PortalBE.dto.RegistrationResponse;
import com.example.PortalBE.services.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class loginController {

    @Autowired
    private loginService loginService;

    public loginController(loginService userService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> handleLogin(@RequestBody LoginRequest request){
        LoginResponse response = loginService.handleLogin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = loginService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
