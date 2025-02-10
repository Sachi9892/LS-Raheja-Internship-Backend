package com.ls_raheja.application_form.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls_raheja.application_form.entity.Degree;
import com.ls_raheja.application_form.entity.EducationMode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QualificationDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Degree degree;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private EducationMode educationMode;

    private String degreeName;
    private String universityName;
    private String specialization;
    private Integer yearOfPassing;
    private Double cgpa;
}
