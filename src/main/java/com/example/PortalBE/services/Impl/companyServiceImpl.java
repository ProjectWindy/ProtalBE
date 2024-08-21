package com.example.PortalBE.services.Impl;


import com.example.PortalBE.dto.CompanyInformationDTO;
import com.example.PortalBE.dto.CompanyRequest;
import com.example.PortalBE.dto.HrDTO;
import com.example.PortalBE.entity.Company;
import com.example.PortalBE.entity.Hr;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.companyRepository;
import com.example.PortalBE.services.CloudinaryImageService;
import com.example.PortalBE.services.companyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class companyServiceImpl implements companyService {

    @Autowired
    private companyRepository companyRepository;
    private ModelMapper modelMapper;
    private CloudinaryImageService cloudinaryImageService;

    @Override
    @Transactional
    public CompanyInformationDTO createInformation(CompanyRequest request){
        String urlImage = cloudinaryImageService.uploadImage(request.getImageInfor());

        Company companyEntity = modelMapper.map(request, Company.class);
        companyEntity.setImage(urlImage);
        Company companySave = companyRepository.save(companyEntity);
        CompanyInformationDTO response = modelMapper.map(companySave,CompanyInformationDTO.class );
        return response;
    }

    @Override
    @Transactional
    public CompanyInformationDTO getInformationbyId(Integer informationId){
        Company companyEntity = companyRepository.findById(informationId).orElseThrow(() ->
                new ResourceNotFoundException("Infor does not exist"));

        CompanyInformationDTO reponse = modelMapper.map(companyEntity,CompanyInformationDTO.class );
        return reponse;
    }

    @Override
    @Transactional
    public Page<CompanyInformationDTO> getAllInformation(int page, int size) {
        // Tạo một đối tượng Pageable để chỉ định trang và kích thước trang
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng phương thức findAll của repository với Pageable để trả về kết quả phân trang
        Page<Company> companyPage = companyRepository.findAll(pageable);

        // Chuyển đổi trang kết quả sang đối tượng Page của UserDto
        Page<CompanyInformationDTO> CompanyInformationDTOpage = companyPage.map(companyEntity -> {
            CompanyInformationDTO informationDTO = modelMapper.map(companyEntity, CompanyInformationDTO.class);
            return informationDTO;
        });

        return CompanyInformationDTOpage;
    }

    @Override
    @Transactional
    public CompanyInformationDTO updateInformation(CompanyRequest request){
        Company entity = companyRepository.findById(request.getId()).orElseThrow(() ->
        new ResourceNotFoundException("Information doesn't exist"));

        if(request.getImageInfor() == null) {
            request.setImage(entity.getImage());
        } else {
            String urlImage = cloudinaryImageService.uploadImage(request.getImageInfor());
            request.setImage(urlImage);
        }

        modelMapper.map(request, entity);
        Company savedCompany = companyRepository.save(entity);

        CompanyInformationDTO res = modelMapper.map(savedCompany, CompanyInformationDTO.class);
        return res;
    }

    @Override
    @Transactional
    public void deleteInformation(Integer CompanyId){
        companyRepository.findById(CompanyId).orElseThrow(() -> new ResourceNotFoundException("This Information could not be found!!"));
        companyRepository.deleteById(CompanyId);
    }

}
