package com.ls_raheja.application_form.entity.degree;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CourseTaught {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "courseTaught")
    @JsonBackReference
    private Applicant applicant;

    private String collegeName;
    private String className;
    private String subjectName;
    private String degreeType;
    private String typeOfContract;

    @Temporal(TemporalType.DATE)
    private LocalDate fromDate;

    @Temporal(TemporalType.DATE)
    private LocalDate toDate;

    private String yearOfExp;
    private Double lastSalary;

    private boolean approvedByUniversity;
    private String letterNO;

    @Temporal(TemporalType.DATE)
    private LocalDate letterDate;

}
