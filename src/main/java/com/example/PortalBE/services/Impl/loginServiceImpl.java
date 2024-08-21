package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.*;
import com.example.PortalBE.entity.User;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.usersRepository;
import com.example.PortalBE.services.loginService;
import com.example.PortalBE.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService {

    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final usersRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public loginServiceImpl(JwtUtil jwtUtil, ModelMapper modelMapper, usersRepository userRepo, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse handleLogin(LoginRequest request) {
        User userEntity = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email does not exist"));

        if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())){
            throw new ResourceNotFoundException("Wrong password!");
        }

        UserRespone userRespone = modelMapper.map(userEntity, UserRespone.class);

        String token = jwtUtil.createJwt(userEntity.getEmail());

        LoginResponse response = new LoginResponse();
        response.setJwt(token);
        response.setMessage("Login is successful");
        response.setUser(userRespone);
        return response;
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest request) {
        // Kiểm tra email đã tồn tại chưa
        if(userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("Email already exists");
        }

        // Mã hóa mật khẩu
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Tạo entity user mới và lưu vào database
        User userEntity = new User();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(hashedPassword);
        userEntity.setRole(request.getRole());
        userRepo.save(userEntity);

        // Tạo JWT token
        String token = jwtUtil.createJwt(userEntity.getEmail());

        // Tạo response
        UserRespone userRespone = modelMapper.map(userEntity, UserRespone.class);
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Registration is successful");
        response.setUser(userRespone);

        return response;
    }
}
