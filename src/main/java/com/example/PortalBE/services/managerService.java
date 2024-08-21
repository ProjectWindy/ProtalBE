package com.example.PortalBE.services;

import com.example.PortalBE.dto.ManagerDTO;
import com.example.PortalBE.dto.ManagerRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface managerService {
    @Transactional
    ManagerDTO createNewManager(ManagerRequest request);

    @Transactional
    Page<ManagerDTO> getAllManager(int page, int size);

    @Transactional
    List<ManagerDTO> getAllManagers();

    @Transactional
    ManagerDTO getManagerById(Integer managerId);

    @Transactional
    ManagerDTO updateManager(ManagerRequest request);

    @Transactional
    void deleteManager(Integer managerId);
}
