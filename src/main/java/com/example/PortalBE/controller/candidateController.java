package com.example.PortalBE.controller;

import com.example.PortalBE.dto.CandidateDTO;
import com.example.PortalBE.dto.CandidateRequest;
import com.example.PortalBE.entity.Candidate;
import com.example.PortalBE.services.candidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/candidate")
@RestController
public class candidateController {
    @Autowired
    private candidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<CandidateDTO> create(@ModelAttribute CandidateRequest request) {
        CandidateDTO response = candidateService.createCandidate(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    ResponseEntity<CandidateDTO> updateCandidate(@ModelAttribute CandidateRequest request) {
        CandidateDTO res = candidateService.updateCandidate(request);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteCandidate(@PathVariable("id") Integer candidateId){
        candidateService.deleteCandidate(candidateId);
        Map<String, String> res =Map.of("status delete", "Delete candidate success");
        return ResponseEntity.ok(res);
    }
    @GetMapping("/")
    public ResponseEntity<Page<CandidateDTO>> getAllCandidate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<CandidateDTO> candidatePage = candidateService.getAllcandidate(page, size);
        return new ResponseEntity<>(candidatePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CandidateDTO> getCandidateById(@PathVariable("id") Integer candidateId) {
        CandidateDTO response = candidateService.getCandidateById(candidateId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{candidateId}/apply/{jobId}")
    public ResponseEntity<String> applyForJob(@PathVariable("candidateId") int candidateId, @PathVariable("jobId") int jobId) {
        candidateService.ApplyForJob(jobId, candidateId);
        return ResponseEntity.ok().build();
    }
}
