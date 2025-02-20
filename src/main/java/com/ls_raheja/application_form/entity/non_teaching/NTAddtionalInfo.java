package com.ls_raheja.application_form.entity.non_teaching;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NT_AddInfo")
@Getter
@Setter
public class NTAddtionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addId;

    private Integer ets;
    private Integer mts;
    private String motherTounge;
    private String otherLanguage;

    private String joinDate;
    
    private Integer expectedSalary;
    private String comment;

}
