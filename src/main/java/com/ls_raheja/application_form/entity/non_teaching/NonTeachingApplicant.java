package com.ls_raheja.application_form.entity.non_teaching;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Non_Teaching")
@Getter
@Setter
public class NonTeachingApplicant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personal_info_id")
    @ToStringExclude
    private NTPersonalInfo personalInfo;

    @OneToMany(mappedBy = "ntApplicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToStringExclude
    private List<NTQualificationInfo> qualificationInfo = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "add_info_id")
    @ToStringExclude
    private NTAddtionalInfo addInfo;

    @OneToMany(mappedBy = "ntApplicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToStringExclude
    private List<NTWorkExpDetails> workExp = new ArrayList<>();


    private Boolean isFresher;


}
