package com.example.PortalBE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "department")
    private String department;

    @Column(name ="mail")
    private String mail;

    @Column(name = "name")
    private String name;

    @Column(name="phone")
    private int phone;

    @Column(name = "position")
    private String position;
}
