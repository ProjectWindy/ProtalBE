package com.example.PortalBE.controller;

import com.example.PortalBE.dto.ManagerDTO;
import com.example.PortalBE.dto.ManagerRequest;
import com.example.PortalBE.services.managerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/managers")
public class managerController {

    @Autowired
    managerService managerService;

    @PostMapping
    ResponseEntity<ManagerDTO> createNewStudent(@ModelAttribute ManagerRequest request){
        ManagerDTO response = managerService.createNewManager(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Page<ManagerDTO>> getManagers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<ManagerDTO> managersPage = managerService.getAllManager(page, size);
        return new ResponseEntity<>(managersPage, HttpStatus.OK);
    }

    @GetMapping("/no-Pagination")
    ResponseEntity<List<ManagerDTO>> getManagersNoPanigation() {
        List<ManagerDTO> response = managerService.getAllManagers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<ManagerDTO> getManagerByID(@PathVariable("id") Integer managerId) {
        ManagerDTO response = managerService.getManagerById(managerId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    ResponseEntity<ManagerDTO> updateManager(@ModelAttribute ManagerRequest request) {
        ManagerDTO res = managerService.updateManager(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    ResponseEntity<ManagerDTO> updateManagerMethodPost(@ModelAttribute ManagerRequest request) {
        ManagerDTO res = managerService.updateManager(request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteManager(@PathVariable("id") Integer managerId){
        managerService.deleteManager(managerId);
        Map<String, String> res =Map.of("status delete", "Delete manager success");
        return ResponseEntity.ok(res);
    }
}
