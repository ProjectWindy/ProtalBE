package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.JobRequest;
import com.example.PortalBE.dto.JobResponse;
import com.example.PortalBE.entity.Hr;
import com.example.PortalBE.entity.Job;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.jobRepository;
import com.example.PortalBE.services.CloudinaryImageService;
import com.example.PortalBE.services.jobService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class jobServiceImpl implements jobService {
    private ModelMapper modelMapper;
    private jobRepository jobRepository;
    private CloudinaryImageService cloudinaryImageService;
    private com.example.PortalBE.repository.hrRepository hrRepository;

    @Transactional
    @Override
    public JobResponse createJob(JobRequest request){
        String urlImage = cloudinaryImageService.uploadImage(request.getImageJob());

        Job jobEntity = new Job();
        jobEntity.setJobName(request.getJobName());
        jobEntity.setJobCode(request.getJobCode());
        jobEntity.setDescription(request.getDescription());
        jobEntity.setCategory(request.getCategory());
        jobEntity.setRequirement(request.getRequirement());
        jobEntity.setSalary(request.getSalary());
        jobEntity.setImage(urlImage);

        Hr hr = hrRepository.findById(request.getHR_id()).orElseThrow(() ->
                new ResourceNotFoundException("This HR with id " + request.getHR_id() + " doesn't exist"));

        jobEntity.setHr(hr);

        jobRepository.save(jobEntity);

        hr.addJob(jobEntity);
        hrRepository.save(hr);

        JobResponse response = modelMapper.map(jobEntity, JobResponse.class);
        response.setHrName(jobEntity.getHr().getName());
        response.setCreatedAt(jobEntity.getCreatedAt());
        return response;
    }

    @Override
    @Transactional
    public Page<JobResponse> getAllJob(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng phương thức findAll của repository với Pageable để trả về kết quả phân trang
        Page<Job> jobPage = jobRepository.findAll(pageable);

        // Chuyển đổi trang kết quả sang đối tượng Page của UserDto
        Page<JobResponse> jobResponsePage = jobPage.map(jobResponseEntity -> {

            String managerName = jobResponseEntity.getHr().getName();
            JobResponse jobResponse = modelMapper.map(jobResponseEntity, JobResponse.class);

            jobResponse.setHrName(managerName);
            return jobResponse;
        });
        return jobResponsePage;
    }

    @Override
    @Transactional
    public List<JobResponse> getAllJobs(){
        List<Job> entityJob = jobRepository.findAll();

        List<JobResponse> response = new ArrayList<>();
        for(int i = 0; i < entityJob.size(); i++) {
            JobResponse courseRes = modelMapper.map(entityJob.get(i), JobResponse.class);
            response.add(courseRes);
        }
        return response;
    }

    @Override
    @Transactional
    public JobResponse getJobById(Integer jobId) {
        Job jobEntity = jobRepository.findById(jobId).orElseThrow(() ->
                new ResourceNotFoundException("Job does not exist"));

        JobResponse response = modelMapper.map(jobEntity, JobResponse.class);
        response.setHrName(jobEntity.getHr().getName());
        return response;
    }

    @Override
    @Transactional
    public List<JobResponse> getJobByCategory(String category) {
        List<Job> listCourseEntity = jobRepository.findAll();

        List<JobResponse> response = new ArrayList<>();
        for (Job course : listCourseEntity) {
            if (category.equals(course.getCategory())) {
                JobResponse courseResponse = modelMapper.map(course, JobResponse.class);
                response.add(courseResponse);
            }
        }
        return response;
    }

    @Override
    @Transactional
    public void deleteJob(Integer jobId) {
        jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("This job could not be found!!"));
        jobRepository.deleteById(jobId);
    }

    @Override
    @Transactional
    public JobResponse updateJob(JobRequest request) {
        Job jobEntity = jobRepository.findById(request.getId()).orElseThrow(() ->
                new ResourceNotFoundException("This job doesn't exist"));

        if (request.getImageJob() != null) {
            String urlImage = cloudinaryImageService.uploadImage(request.getImageJob());
            jobEntity.setImage(urlImage);
        }

        jobEntity.setJobName(request.getJobName());
        jobEntity.setJobCode(request.getJobCode());
        jobEntity.setSalary(request.getSalary());
        jobEntity.setCategory(request.getCategory());


        System.out.print("check name" + request.getJobName());


        jobRepository.save(jobEntity);

        JobResponse response = modelMapper.map(jobEntity, JobResponse.class);

        response.setHrName(jobEntity.getHr().getName());

        return response;
    }
}
