package com.ls_raheja.application_form.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Builder
@Data
public class CompetitiveExams implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;

    @Column(nullable = true)
    private String examName; // Example: NET, SET, GATE

    @Column(nullable = true)
    private boolean isAppeared; // Whether the applicant appeared for the exam

    @Column(nullable = true)
    private String yearOfPassing;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId")
    @JsonBackReference
    private Applicant applicant;

}
