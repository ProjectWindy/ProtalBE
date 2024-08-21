package com.example.PortalBE.services;

import com.example.PortalBE.dto.CompanyInformationDTO;
import com.example.PortalBE.dto.CompanyRequest;
import com.example.PortalBE.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface companyService {
    @Transactional
    CompanyInformationDTO createInformation(CompanyRequest request);

    @Transactional
    CompanyInformationDTO getInformationbyId(Integer informationId);

    @Transactional
    Page<CompanyInformationDTO> getAllInformation(int page, int size);


    @Transactional
    CompanyInformationDTO updateInformation(CompanyRequest request);

    @Transactional
    void deleteInformation(Integer CompanyId);
}
