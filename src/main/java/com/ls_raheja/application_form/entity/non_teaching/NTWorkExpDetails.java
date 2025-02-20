package com.ls_raheja.application_form.entity.non_teaching;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NT_WorkExp")
@Getter
@Setter
public class NTWorkExpDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workId;

    private String orgName;
    private String position;
    private String workNature;

    private Boolean isCurrentlyWorking;

    private String fromDate;
    private String toDate;

    @Column(name = "reason_of_leaving")
    private String rfl;

    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "nt_applicant_id", nullable = false)
    @JsonBackReference
    private NonTeachingApplicant ntApplicant;
    
}
