package com.example.PortalBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Manager")
public class Manager extends User {
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name ="mail")
    private String mail;

    @Column(name = "image")
    private String image;

//    @OneToMany(mappedBy = "manager", cascade = {
//            CascadeType.ALL
//    }, fetch = FetchType.LAZY, orphanRemoval = true)
//    @Column(name = "list_candidate")
//    @JsonIgnore
//    private Set<Candidate> candidates = new HashSet<>();

}
