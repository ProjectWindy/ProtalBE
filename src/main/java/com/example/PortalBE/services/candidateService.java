package com.example.PortalBE.services;

import com.example.PortalBE.dto.CandidateDTO;
import com.example.PortalBE.dto.CandidateRequest;
import com.example.PortalBE.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface candidateService {

    @Transactional
    CandidateDTO createCandidate(CandidateRequest request);

    @Transactional
    Page<CandidateDTO> getAllcandidate(int page, int size);

    @Transactional
    CandidateDTO getCandidateById(Integer studentId);

    @Transactional
    CandidateDTO updateCandidate(CandidateRequest request);

    @Transactional
    void deleteCandidate(Integer candidateId);

    @Transactional
    void ApplyForJob(int candidateId, int jobId);
}
