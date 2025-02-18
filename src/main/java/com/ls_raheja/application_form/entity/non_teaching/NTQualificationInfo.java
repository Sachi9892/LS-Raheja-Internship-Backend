package com.ls_raheja.application_form.entity.non_teaching;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "NT_Qualification")
@Getter
@Setter
public class NTQualificationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qualiId;

    @Enumerated(EnumType.STRING)
    private NTDegree degree;

    private Double marks;
    private String grade;
    private String yearOfPassing;

    @ManyToOne
    @JoinColumn(name = "nt_applicant_id")
    private NonTeachingApplicant ntApplicant;

}
