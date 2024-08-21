package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.ManagerDTO;
import com.example.PortalBE.dto.ManagerRequest;
import com.example.PortalBE.entity.Manager;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.managerRepository;
import com.example.PortalBE.repository.usersRepository;
import com.example.PortalBE.services.CloudinaryImageService;
import com.example.PortalBE.services.managerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class managerServiceImpl implements managerService {
    private managerRepository managerRepo;
    private usersRepository userRepo;
    private ModelMapper modelMapper;
    private CloudinaryImageService cloudinaryImageService;


    @Override
    @Transactional
    public ManagerDTO createNewManager(ManagerRequest request) {
        String urlImage = cloudinaryImageService.uploadImage(request.getAvatar());
        //         Hash the password before saving to the database
        BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
        String hashedPassword = hashPassword.encode(request.getPassword());

        Manager managerEntity = modelMapper.map(request, Manager.class);
        managerEntity.setImage(urlImage);
        managerEntity.setPassword(hashedPassword);
        Manager ManagerSave = managerRepo.save(managerEntity);

        ManagerDTO response = modelMapper.map(ManagerSave, ManagerDTO.class);

        return response;
    }

    @Override
    @Transactional
    public Page<ManagerDTO> getAllManager(int page, int size) {
        // Tạo một đối tượng Pageable để chỉ định trang và kích thước trang
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng phương thức findAll của repository với Pageable để trả về kết quả phân trang
        Page<Manager> managersPage = managerRepo.findAll(pageable);

        // Chuyển đổi trang kết quả sang đối tượng Page của UserDto
        Page<ManagerDTO> managerDtoPage = managersPage.map(managerEntity -> {
            ManagerDTO managerDTO = modelMapper.map(managerEntity, ManagerDTO.class);
            managerDTO.setPassword("");
            return managerDTO;
        });

        return managerDtoPage;
    }



    @Override
    @Transactional
    public List<ManagerDTO> getAllManagers() {
        List<Manager> entityManagers = managerRepo.findAll();

        List<ManagerDTO> response = new ArrayList<>();
        for(int i = 0; i < entityManagers.size(); i++) {
            ManagerDTO managerDTO = modelMapper.map(entityManagers.get(i), ManagerDTO.class);
            response.add(managerDTO);
        }
        return response;
    }

    @Override
    @Transactional
    public ManagerDTO getManagerById(Integer managerId) {
        Manager managerEntity = managerRepo.findById(managerId).orElseThrow(() ->
                new ResourceNotFoundException("manager does not exist"));

        ManagerDTO response = modelMapper.map(managerEntity, ManagerDTO.class);
        return response;
    }

    @Override
    @Transactional
    public ManagerDTO updateManager(ManagerRequest request) {
        Manager entity = managerRepo.findById(request.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Manager doesn't exist"));

        if(request.getAvatar() == null) {
            request.setImage(entity.getImage());
        } else {
            String urlImage = cloudinaryImageService.uploadImage(request.getAvatar());
            request.setImage(urlImage);
        }
        if(request.getPassword() == null){
            request.setPassword(entity.getPassword());
        }else{
            BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
            String hashedPassword = hashPassword.encode(request.getPassword());
            request.setPassword(hashedPassword);
        }

        modelMapper.map(request, entity);
        Manager savedManager = managerRepo.save(entity);

        ManagerDTO res = modelMapper.map(savedManager, ManagerDTO.class);

        return res;
    }

    @Override
    @Transactional
    public void deleteManager(Integer managerId) {
        userRepo.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("This user could not be found!!"));
        managerRepo.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("This manager could not be found!!"));
        userRepo.deleteById(managerId);
        managerRepo.deleteById(managerId);
    }
}

