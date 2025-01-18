package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.Degree;
import com.ls_raheja.application_form.entity.EducationMode;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QualificationDto {

    private Degree degree;
    private EducationMode educationMode;

    private String degreeName;
    private String universityName;
    private String specialization;
    private LocalDate yearOfPassing;
    private Double cgpa;

    private List<CompetitiveExamsDto> competitiveExams;

}
