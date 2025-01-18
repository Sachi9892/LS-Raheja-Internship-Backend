package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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
    private Applicant applicant;

    @ElementCollection
    @CollectionTable(
            name = "competitive_exams",
            joinColumns = @JoinColumn(name = "qualification_id")
    )
    private List<CompetitiveExams> competitiveExams;

    private String degreeName;

    private String universityName;

    private String specialization;

    @Temporal(TemporalType.DATE)
    private LocalDate yearOfPassing;

    private Double cgpa;
}
