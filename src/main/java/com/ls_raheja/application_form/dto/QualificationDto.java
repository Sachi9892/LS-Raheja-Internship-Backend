package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class QualificationDto {

    private String degree;
    private String educationMode;
    private String degreeName;
    private String universityName;
    private String specialization;
    private LocalDate yearOfPassing;
    private Double cgpa;

    private List<CompetitiveExamsDto> competitiveExams;

}
