package com.ls_raheja.application_form.entity.non_teaching;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Column(name = "is_working")
    private Boolean isCurrentlyWorking;

    @Temporal(TemporalType.DATE)
    private LocalDate fromDate;

    @Temporal(TemporalType.DATE)
    private LocalDate toDate;

    @Column(name = "reason_of_leaving")
    private String rfl;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "nt_applicant_id")
    private NonTeachingApplicant ntApplicant;

}
