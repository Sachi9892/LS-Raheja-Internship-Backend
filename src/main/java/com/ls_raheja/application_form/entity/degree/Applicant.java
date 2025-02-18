package com.ls_raheja.application_form.entity.degree;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    @JsonManagedReference
    @ToString.Exclude
    private List<Qualifications> qualifications = new ArrayList<>();

    // One Applicant can have multiple Work Experiences
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    @JsonManagedReference
    @ToString.Exclude
    private List<WorkExperience> workExperience = new ArrayList<>();

    // One Applicant may have appeared multiple exams
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    @JsonManagedReference
    @ToString.Exclude
    private List<CompetitiveExams> competitiveExams = new ArrayList<>();

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "award_id", referencedColumnName = "awardId") // Fix: change column name
    @JsonManagedReference
    @ToString.Exclude
    private Award award;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_taught_id", referencedColumnName = "id") // Fix: change column name
    @JsonManagedReference
    @ToString.Exclude
    private CourseTaught courseTaught;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "applicant")
    @JsonManagedReference
    @ToString.Exclude
    private List<ResearchPublication> researchPapers;

    private String refrenceName;
    
    private Double expectedSalary;

    private String appliedFor;

    private String extraActivity;

}
