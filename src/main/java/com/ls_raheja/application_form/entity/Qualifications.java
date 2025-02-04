package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity(name = "qualifications")
@Data
public class Qualifications implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qualificationId;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Enumerated(EnumType.STRING)
    private EducationMode educationMode;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId")
    @JsonBackReference
    private Applicant applicant;

    private String degreeName;

    private String universityName;

    private String specialization;

    private int yearOfPassing;

    private Double cgpa;

}
