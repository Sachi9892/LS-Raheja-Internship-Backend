package com.ls_raheja.application_form.dto.degree_dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTaughtDto {
    
    private String collegeName;
    private String className;
    private String subjectName;
    private String degreeType;
    private String typeOfContract;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String yearOfExp;
    private Double lastSalary;

    private boolean approvedByUniversity;
    private String letterNO;

    private LocalDate letterDate;

}
