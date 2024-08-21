package com.example.PortalBE.controller;

import com.example.PortalBE.dto.JobRequest;
import com.example.PortalBE.dto.JobResponse;
import com.example.PortalBE.services.jobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/job")
public class jobController {

    @Autowired
    private jobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createNewJob(@ModelAttribute JobRequest request){
        JobResponse response = jobService.createJob(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<Page<JobResponse>> getJob(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<JobResponse> jobPage = jobService.getAllJob(page, size);
        return new ResponseEntity<>(jobPage, HttpStatus.OK);
    }

    @GetMapping("/no-Pagination")
    ResponseEntity<List<JobResponse>> getJobNoPanigation() {
        List<JobResponse> response = jobService.getAllJobs();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<JobResponse> getJobByID(@PathVariable("id") Integer jobId) {
        JobResponse response = jobService.getJobById(jobId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobResponse>> getJobByCategory(@PathVariable String category) {
        List<JobResponse> courses = jobService.getJobByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobResponse> updateJob(@ModelAttribute JobRequest request){
        JobResponse response = jobService.updateJob(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteJob(@PathVariable("id") Integer jobId){
        jobService.deleteJob(jobId);
        Map<String, String> res =Map.of("status delete", "Delete job success");
        return ResponseEntity.ok(res);
    }

}
