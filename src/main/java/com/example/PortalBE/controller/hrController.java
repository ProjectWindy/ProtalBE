package com.example.PortalBE.controller;

import com.example.PortalBE.dto.HrDTO;
import com.example.PortalBE.dto.HrRequest;
import com.example.PortalBE.repository.hrRepository;
import com.example.PortalBE.services.hrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/hr")
public class hrController {
    @Autowired
    hrService hrService;

    @PostMapping("/create")
    ResponseEntity<HrDTO> createNewHr(@ModelAttribute HrRequest request){
        HrDTO response = hrService.createNewHr(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<Page<HrDTO>> getAllHr(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<HrDTO> HrPage  = hrService.getAllHr(page, size);
        return new ResponseEntity<>(HrPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<HrDTO> getHrByID(@PathVariable("id") Integer hrId) {
        HrDTO response = hrService.getHrById(hrId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/{id}")
    ResponseEntity<HrDTO> updateHr(@ModelAttribute HrRequest request) {
        HrDTO res = hrService.updateHr(request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteManaer(@PathVariable("id") Integer hrId){
        hrService.deleteHr(hrId);
        Map<String, String> res =Map.of("status delete", "Delete hr success");
        return ResponseEntity.ok(res);
    }
}
