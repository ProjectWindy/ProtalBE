package com.example.PortalBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String jobName;

    @Column(name = "code")
    private String jobCode;

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "category")
    private String category;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "Hr_id")
    private Hr hr;

    @ManyToMany
    @JoinTable(name = "job_candidate",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private Set<Candidate> candidates = new HashSet<>();

    public void addCandidate(Candidate candidate) {
        this.candidates.add(candidate);
        candidate.getAppliedJobs().add(this); // Đảm bảo cả hai phía của mối quan hệ đều được cập nhật
    }

}

