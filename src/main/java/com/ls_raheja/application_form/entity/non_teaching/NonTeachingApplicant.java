package com.ls_raheja.application_form.entity.non_teaching;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @OneToOne
    private NTPersonalInfo personalInfo;

    @OneToMany(mappedBy = "ntApplicant" , cascade = CascadeType.ALL)
    private List<NTQualificationInfo> qualificationInfo;

    @OneToOne
    private NTAddtionalInfo addInfo;

    @OneToMany(mappedBy = "ntApplicant" , cascade = CascadeType.ALL)
    private List<NTWorkExpDetails> workExp;

    

}
