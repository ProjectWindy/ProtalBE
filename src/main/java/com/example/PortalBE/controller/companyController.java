package com.example.PortalBE.controller;

import com.example.PortalBE.dto.CompanyRequest;
import com.example.PortalBE.services.companyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PortalBE.dto.CompanyInformationDTO;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/information")
public class companyController {
    @Autowired
    companyService companyService;

    @PostMapping("/create")
    ResponseEntity<CompanyInformationDTO> createInformationCompany(@ModelAttribute CompanyRequest request){
        CompanyInformationDTO response = companyService.createInformation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<Page<CompanyInformationDTO>> getAllInformationCompany(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<CompanyInformationDTO> informationPage = companyService.getAllInformation(page, size);
        return new ResponseEntity<>(informationPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CompanyInformationDTO> getInformationCompanybyId(@PathVariable("id") Integer informationId) {
        CompanyInformationDTO response = companyService.getInformationbyId(informationId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<CompanyInformationDTO> updateInformationCompany(@ModelAttribute CompanyRequest request) {
        CompanyInformationDTO res = companyService.updateInformation(request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteInformationCompany(@PathVariable("id") Integer informationId){
        companyService.deleteInformation(informationId);
        Map<String, String> res =Map.of("status delete", "Delete information success");
        return ResponseEntity.ok(res);
    }
}
