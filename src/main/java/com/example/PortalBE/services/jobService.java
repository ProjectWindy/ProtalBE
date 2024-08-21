package com.example.PortalBE.services;

import com.example.PortalBE.dto.JobRequest;
import com.example.PortalBE.dto.JobResponse;
import com.example.PortalBE.repository.jobRepository;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface jobService {


    JobResponse createJob(JobRequest request);

    Page<JobResponse> getAllJob(int page, int size);


    List<JobResponse> getAllJobs();

    JobResponse getJobById(Integer jobId);

    List<JobResponse> getJobByCategory(String category);

    void deleteJob(Integer jobId);

    JobResponse updateJob(JobRequest request);

}
