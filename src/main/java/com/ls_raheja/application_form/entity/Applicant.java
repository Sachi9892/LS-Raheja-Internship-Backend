package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Applicant")
@Data
public class Applicant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicantId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", referencedColumnName = "infoId")
    @JsonManagedReference
    @ToString.Exclude
    private PersonalInfo personalInfo;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phd_id", referencedColumnName = "pdhId")
    @JsonManagedReference
    @ToString.Exclude
    private Phd phd;


    // One Applicant can have multiple Qualifications
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant", orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Qualifications> qualifications = new ArrayList<>();


    // One Applicant can have multiple Work Experiences
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant", orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<WorkExperience> workExperience = new ArrayList<>();


    // One Applicant may have appeared multiple exams
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant", orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<CompetitiveExams> competitiveExams = new ArrayList<>();

    private String resumeFileLocation;

}
