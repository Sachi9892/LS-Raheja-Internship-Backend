package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


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

    @Column(nullable = true)
    private String universityName;

    @Column(nullable = true)
    private Integer yearOfPassing;

    @Column(name = "scopus" , nullable = true)
    private Integer scopusIndexedPublications;

    @Column(nullable = true)
    private String scopusId;

    @Column(nullable = true , columnDefinition = "BOOLEAN")
    private Boolean presentedInConference;

    @Column(name = "wos" , nullable = true)
    private Integer wosIndexedPublications;

    @Column(nullable = true)
    private String wosId;
}
