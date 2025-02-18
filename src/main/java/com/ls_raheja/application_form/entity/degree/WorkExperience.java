package com.ls_raheja.application_form.entity.degree;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "work_experience")
@Data
public class WorkExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exp_id")
    private Long experienceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private NoticePeriod noticePeriod;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId")
    @JsonBackReference
    private Applicant applicant;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean isFresher;

    @Column(name = "org_name", nullable = true)
    private String organizationName;

    @Column(name = "is_working", nullable = true, columnDefinition = "BOOLEAN")
    private Boolean isCurrentlyWorking;

    @Column(nullable = true)
    private String jobTitle;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private LocalDate fromDate;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private LocalDate toDate;

    @Column(name = "Salary", nullable = true)
    private Double currentSalary;

}
