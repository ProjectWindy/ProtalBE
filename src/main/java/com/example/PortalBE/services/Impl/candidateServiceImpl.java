package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.CandidateDTO;
import com.example.PortalBE.dto.CandidateRequest;
import com.example.PortalBE.dto.JobDTO;
import com.example.PortalBE.dto.TimeSheetDTO;
import com.example.PortalBE.entity.Candidate;
import com.example.PortalBE.entity.Job;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.candidateRepository;
import com.example.PortalBE.repository.jobRepository;
import com.example.PortalBE.repository.usersRepository;
import com.example.PortalBE.services.CloudinaryImageService;
import com.example.PortalBE.services.candidateService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;


import org.springframework.data.domain.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class candidateServiceImpl implements candidateService {

    private candidateRepository candidateRepository;
    private usersRepository usersRepository;
    private ModelMapper modelMapper;
    private CloudinaryImageService cloudinaryImageService;
    private jobRepository jobRepository;

    @Override
    @Transactional
    public CandidateDTO createCandidate(CandidateRequest request) {
        String urlImage = cloudinaryImageService.uploadImage(request.getImageCV());
        //         Hash the password before saving to the database
        BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
        String hashedPassword = hashPassword.encode(request.getPassword());

        Candidate candidateEntity = modelMapper.map(request, Candidate.class);
        candidateEntity.setCv(urlImage);
        candidateEntity.setPassword(hashedPassword);
        Candidate candidateSave = candidateRepository.save(candidateEntity);

        CandidateDTO response = modelMapper.map(candidateSave, CandidateDTO.class);

        return response;

    }

    @Override
    @Transactional
    public Page<CandidateDTO> getAllcandidate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Candidate> candidatePage = candidateRepository.findAll(pageable);

        Page<CandidateDTO> candidateDtoPage = candidatePage.map(candidateEntity -> {
            CandidateDTO candidateDTO = modelMapper.map(candidateEntity, CandidateDTO.class);
            candidateDTO.setPassword("");
            List<JobDTO> jobDTOs = candidateEntity.getAppliedJobs().stream()
                    .map(job -> modelMapper.map(job, JobDTO.class))
                    .collect(Collectors.toList());
            candidateDTO.setListJobApply(jobDTOs);
            return candidateDTO;
        });

        return candidateDtoPage;
    }


    @Override
    @Transactional
    public CandidateDTO getCandidateById(Integer candidateId) {
        Candidate candidateEntity = candidateRepository.findById(candidateId).orElseThrow(() ->
                new ResourceNotFoundException("Candidate with id " + candidateId + " not found"));

        CandidateDTO candidateDto = modelMapper.map(candidateEntity, CandidateDTO.class);
        List<JobDTO> candidateResponses = candidateEntity.getAppliedJobs().stream()
                .map(job -> modelMapper.map(job, JobDTO.class))
                .collect(Collectors.toList());
        candidateDto.setListJobApply(candidateResponses);

        return candidateDto;
    }

    @Override
    @Transactional
    public CandidateDTO updateCandidate(CandidateRequest request) {
        Candidate entity = candidateRepository.findById(request.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Candidate doesn't exist"));
        if(request.getImageCV() == null) {
            request.setCv(entity.getCv());
        } else {
            String urlImage = cloudinaryImageService.uploadImage(request.getImageCV());
            request.setCv(urlImage);
        }
        if(request.getPassword() == null){
            request.setPassword(entity.getPassword());
        }else{
            BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
            String hashedPassword = hashPassword.encode(request.getPassword());
            request.setPassword(hashedPassword);
        }
        modelMapper.map(request, entity);
        Candidate savedCandidate = candidateRepository.save(entity);
        CandidateDTO res = modelMapper.map(savedCandidate, CandidateDTO.class);
        return res;
    }

    @Override
    @Transactional
    public void deleteCandidate(Integer cadidateId) {
        usersRepository.findById(cadidateId).orElseThrow(() -> new ResourceNotFoundException("This user could not be found!!"));
        candidateRepository.findById(cadidateId).orElseThrow(() -> new ResourceNotFoundException("This candidate could not be found!!"));
        usersRepository.deleteById(cadidateId);
        candidateRepository.deleteById(cadidateId);
    }

    @Override
    @Transactional
    public void ApplyForJob(int jobId, int candidateId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() ->
                new ResourceNotFoundException("Job with id " + jobId + " doesn't exist"));

        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() ->
                new ResourceNotFoundException("Candidate with id " + candidateId + " doesn't exist"));

        // Kiểm tra xem ứng viên đã nộp đơn cho công việc này chưa
        if (job.getCandidates().contains(candidate)) {
            throw new DuplicateKeyException("Candidate has already applied for this job");
        }

        job.addCandidate(candidate); // Đảm bảo phương thức này chỉ thêm Candidate, không thay đổi trường Hr
        candidate.addJob(job);
        jobRepository.save(job); // Lưu thay đổi
        candidateRepository.save(candidate);
    }


}
