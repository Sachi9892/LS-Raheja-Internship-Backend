package com.ls_raheja.application_form.dto.non_teaching;

import com.ls_raheja.application_form.entity.non_teaching.NTDegree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NTQualificationInfoDTO {

    private NTDegree degree;

    private String universityName;
    private Double marks;
    private String grade;
    private String yearOfPassing;

}
