package com.ls_raheja.application_form.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;



@Embeddable
@Builder
@Data
public class CompetitiveExams implements Serializable {

    @Column(nullable = true)
    private String examName; // Example: NET, SET, GATE
    @Column(nullable = true)
    private boolean isAppeared; // Whether the applicant appeared for the exam
    @Column(nullable = true)
    private String yearOfPassing;
}
