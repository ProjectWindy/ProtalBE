package com.example.PortalBE.services;

import com.example.PortalBE.dto.HrDTO;
import com.example.PortalBE.dto.HrRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface hrService {


    @Transactional
    HrDTO createNewHr(HrRequest request);

    @Transactional
    Page<HrDTO> getAllHr(int page, int size);

    @Transactional
    HrDTO getHrById(Integer managerId);

    @Transactional
    HrDTO updateHr(HrRequest request);

    @Transactional
    void deleteHr(Integer hrId);
}
