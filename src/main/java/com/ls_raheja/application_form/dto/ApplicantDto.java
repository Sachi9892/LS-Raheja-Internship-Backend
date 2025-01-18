package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.util.List;


@Data
public class ApplicantDto {


    private String role;

    private PersonalInfoDto personalInfo;
    private AddressDto address;
    private List<QualificationDto> qualifications;
    private List<WorkExperienceDto> workExperience;

    private PhdDto phd;
}
