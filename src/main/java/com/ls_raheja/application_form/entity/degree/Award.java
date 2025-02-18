package com.ls_raheja.application_form.entity.degree;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long awardId;

    @OneToOne(mappedBy = "award")
    @JsonBackReference
    private Applicant applicant;

    private String title;
    private String orgName;
    private String nature;
    private String orgRecorgnize;

}
