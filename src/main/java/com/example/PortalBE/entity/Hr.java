package com.example.PortalBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "HR")
public class Hr extends User {
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name ="mail")
    private String mail;

    @Column(name ="position")
    private String position;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "hr", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();;

    public void addJob(Job Job){
        this.jobs.add(Job);
        Job.setHr(this);
    }
}

