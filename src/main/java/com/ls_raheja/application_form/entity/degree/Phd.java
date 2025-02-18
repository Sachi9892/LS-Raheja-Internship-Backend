package com.ls_raheja.application_form.entity.degree;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Data
public class Phd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pdhId;

    @Enumerated(EnumType.STRING)
    private PhdStatus status;

    @OneToOne(mappedBy = "phd" , cascade = CascadeType.ALL)
    @JsonBackReference
    private Applicant applicant;

    @Column(nullable = true)
    private String universityName;

    @Column(nullable = true)
    private Integer yearOfPassing;

}
