package com.example.PortalBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "candidate")
public class Candidate extends User {
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "category")
    private String category;

    @Column(name = "experience")
    private String experience;

    @Column(name ="cv")
    private String cv;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {
//            CascadeType.MERGE,
//            CascadeType.PERSIST
//    })
//    @JoinTable(
//            name = "job_candidate",
//            joinColumns = @JoinColumn(name = "candidate_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_id")
//    )
    @ManyToMany(mappedBy = "candidates")
    private Set<Job> appliedJobs = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manager_id")
//    private Manager manager;

    public void addJob(Job job) {
        this.appliedJobs.add(job);
        job.getCandidates().add(this); // Đảm bảo tính nhất quán hai chiều
    }

}
