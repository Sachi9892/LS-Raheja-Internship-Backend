package com.ls_raheja.application_form.entity.degree;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResearchPublication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reasearchId;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId")
    @JsonBackReference
    private Applicant applicant;

    @Column(name = "scopus", nullable = true)
    private Integer scopusIndexedPublications;

    @Column(nullable = true)
    private String scopusId;

    @Column(nullable = true, columnDefinition = "BOOLEAN")
    private Boolean presentedInConference;

    private String title;

    private String nameOfJournal;

    @Temporal(TemporalType.DATE)
    private LocalDate yearOfPublication;

    private Integer numberOfApproved;
}
