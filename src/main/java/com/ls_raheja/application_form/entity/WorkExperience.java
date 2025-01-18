package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Entity(name = "work_experience")
@Data
public class WorkExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long experienceId;

    @Enumerated(EnumType.STRING)
    private NoticePeriod noticePeriod;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId")
    private Applicant applicant;

    private boolean isFresher;

    private String organizationName;

    private boolean isCurrentlyWorking;

    private String jobTitle;

    @Temporal(TemporalType.DATE)
    private LocalDate fromDate;

    @Temporal(TemporalType.DATE)
    private LocalDate toDate;

    private Double currentSalary;
}
