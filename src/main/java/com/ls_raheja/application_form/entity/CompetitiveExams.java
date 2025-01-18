package com.ls_raheja.application_form.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
@Builder
@Data
public class CompetitiveExams implements Serializable {

    private String examName; // Example: NET, SET, GATE
    private boolean isAppeared; // Whether the applicant appeared for the exam

    @Temporal(TemporalType.DATE)
    private LocalDate yearOfPassing;
}
