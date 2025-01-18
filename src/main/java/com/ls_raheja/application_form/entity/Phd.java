package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
public class Phd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pdhId;

    @Enumerated(EnumType.STRING)
    private PhdStatus status;

    @OneToOne(mappedBy = "phd")
    private Applicant applicant;

    private String universityName;

    @Temporal(TemporalType.DATE)
    private LocalDate yearOfPassing;

    private int scopusIndexedPublications;

    private String scopusId;

    private boolean presentedInConference;

    private int wosIndexedPublications;

    private String wosId;
}
